package game;

import biuoop.KeyboardSensor;
import game.animation.GameOverAnimation;
import game.animation.KeyPressStoppableAnimation;
import game.animation.YouWinAnimation;
import levels.LevelInformation;
import listener.Counter;
import listener.ScoreTrackingListener;

import java.util.List;

/**
 * @author Eden Cohen.
 * This class is in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {

    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;

    /**
     * Constructor.
     * @param ar animation runner.
     * @param ks keyBoardSensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Runs each level.
     * @param levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {

        // Add Score Tracking Listeners and score indicator- start with score 0.
        ScoreTrackingListener score = new ScoreTrackingListener(new Counter(0));

        // Run each level
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, score);
            level.initialize();

            // Run the level while there are balls and blocks in the game.
            while (level.getRemainedBalls().getValue() != 0 && level.getRemainedBlocks().getValue() != 0) {
                level.run();
            }

            // If there are no more blocks, move to the next level.
            if (level.getRemainedBlocks().getValue() == 0) {
                continue;
            }

            // If there are no more balls, end the game.
            if (level.getRemainedBalls().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new GameOverAnimation(score)));

                // If the player pressed SPACE the gui should be closed.
                this.animationRunner.getGui().close();
                break;
            }

        }

        // The player won - passed all levels.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new YouWinAnimation(score)));

        // If the player pressed SPACE the gui should be closed.
        this.animationRunner.getGui().close();
    }
}