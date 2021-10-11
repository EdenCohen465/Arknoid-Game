// ID:318758778
package game;

import game.animation.Animation;
import game.animation.CountdownAnimation;
import game.animation.KeyPressStoppableAnimation;
import game.animation.PauseScreen;
import levels.LevelInformation;
import logics.Collidable;
import logics.GameEnvironment;
import logics.Sprite;
import logics.SpriteCollection;
import objects.Ball;
import objects.Block;
import objects.Rectangle;
import objects.Point;
import objects.Paddle;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import listener.Counter;
import listener.ScoreTrackingListener;
import listener.ScoreIndicator;
import listener.BlockRemover;
import listener.BallRemover;

import java.util.ArrayList;
import java.awt.Color;

/**
 * @author Eden Cohen
 * This class holds the sprites and the collidables, and in charge of the game animation.
 */
public class GameLevel implements Animation {

    // Constants:
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BORDERS_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 15;
    private static final int NO_BLOCKS_TO_REMOVE = 0;
    private static final int NO_BALLS = 0;
    private static final Color BORDERS_COLOR = new Color(51, 51, 51);
    private static final int ALL_BLOCKS_REMOVED_SCORE = 100;

    // Members:
    private final SpriteCollection sprites;
    private final ArrayList<Collidable> collidableArrayList;
    private GameEnvironment environment;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private final ScoreTrackingListener score;
    private final AnimationRunner runner;
    private Boolean isRunning;
    private KeyboardSensor keyboard;
    private final LevelInformation levelInformation;

    /**
     * Constructor.
     * @param level - current level of the game.
     * @param animationRunner - to run the animation.
     * @param keyboard - sensor.
     * @param score - tracking on the player's score.
     */
    public GameLevel(LevelInformation level, AnimationRunner animationRunner, KeyboardSensor keyboard,
                     ScoreTrackingListener score) {
        this.levelInformation = level;
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.sprites = new SpriteCollection();
        this.collidableArrayList = new ArrayList<>();
        this.score = score;
    }

    /**
     * @return the number of the remain balls in the game.
     */
    public Counter getRemainedBalls() {
        return this.remainedBalls;
    }

    /**
     * @return the number of the remain blocks in the game.
     */
    public Counter getRemainedBlocks() {
        return this.remainedBlocks;
    }

    /**
     * @param c the collidable to add to the game environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s spirite to add to the sprite collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * In charge of setting up the game - creating the gui, blocks, balls and paddle.
     */
    public void initialize() {
        LevelInformation level = this.levelInformation;

        // Add counters to the blocks and to the balls.
        this.remainedBlocks = new Counter(level.blocks().size());
        this.remainedBalls = new Counter(level.numberOfBalls());

        // Initialize the game.
        this.environment = new GameEnvironment(this.collidableArrayList);


        // Create Blocks in the borders.
        Block blockTop = new Block(new Rectangle(new Point(0, 15), WIDTH, BORDERS_WIDTH), BORDERS_COLOR);
        Block deathRegion =
                new Block(new Rectangle(new Point(0, HEIGHT - 1), WIDTH, BORDERS_WIDTH), BORDERS_COLOR);
        Block blockLeft = new Block(new Rectangle(new Point(0, 0), BORDERS_WIDTH, HEIGHT), BORDERS_COLOR);
        Block blockRight =
                new Block(new Rectangle(new Point(WIDTH - BORDERS_WIDTH, 0), BORDERS_WIDTH,
                        HEIGHT), BORDERS_COLOR);
        Block backGround = (Block) level.getBackground();

        // Add the blocks to the game:
        backGround.addToGame(this);

        // Add the draw background to the game:
        this.sprites.addSprite(this.levelInformation.getDrawBackground());

        deathRegion.addToGame(this);
        blockLeft.addToGame(this);
        blockRight.addToGame(this);
        blockTop.addToGame(this);

        // Add Score Tracking Listeners and score indicator- start with score 0.
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        // Add block remover and ball remover.
        BlockRemover blockremover = new BlockRemover(this, this.remainedBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);

        // Register the ball remover as listener to the deathRegion.
        deathRegion.addHitListener(ballRemover);

        // Create balls and add them to the game
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball newBall = level.balls().get(i);
            // Set velocities to each ball.
            newBall.setVelocity(level.initialBallVelocities().get(i));

            // Add the balls to the game.
            newBall.addToGame(this);
            newBall.setEnv(this.environment);

            // Register the ball remover as listener to each ball.
            newBall.addHitListener(ballRemover);
        }

        // Add paddle
        KeyboardSensor keyboard1 = this.runner.getGui().getKeyboardSensor();
        Paddle paddle =
                new Paddle(new Rectangle(level.paddleLocation(),
                        level.paddleWidth(), PADDLE_HEIGHT), keyboard1, level.paddleSpeed());
        paddle.addToGame(this);

        // Add each locks to the game.
        for (int i = 0; i < level.blocks().size(); i++) {

            Block b = level.blocks().get(i);

            // Add the blocks to the game.
            b.addToGame(this);

            // Add block remover and the score tracker as listeners to the each block.
            b.addHitListener(blockremover);
            b.addHitListener(this.score);

        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.isRunning = true;
        this.runner.run(this);
    }

    /**
     * Remove the given collidable from the game.
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidableArrayList.remove(c);
    }

    /**
     * Remove the given sprites from the game.
     * @param s sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteCollection().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.levelInformation.getBackground().drawOn(d);

        // If all balls or blocks remains in the game' it should stop.
        if (this.remainedBlocks.getValue() == NO_BLOCKS_TO_REMOVE || this.remainedBalls.getValue() == NO_BALLS) {

            // If all balls has been removed- add 100 points to the score.
            if (this.remainedBlocks.getValue() == NO_BLOCKS_TO_REMOVE) {
                this.score.getCurrentScore().increase(ALL_BLOCKS_REMOVED_SCORE);
            }

            // Close the game.
            //this.runner.getGui().close();
            this.isRunning = false;
        }

        // If the "p" keyboard was pressed, pause the game.
        this.keyboard = this.runner.getGui().getKeyboardSensor();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.isRunning;
    }

    @Override
    public String getAnimationName() {
        return this.levelInformation.levelName();
    }
}
