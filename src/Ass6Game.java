// ID: 318758778

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;
import game.GameFlow;
import levels.LevelInformation;
import levels.FinalFourLevel;
import levels.DirectHitLevel;
import levels.Green3Level;
import levels.WideEasyLevel;

import java.util.ArrayList;

/**
 * @author Eden Cohen
 * Create and run the game.
 */
public class Ass6Game {

    /**
     * run the game.
     * @param args from command line.
     */
    public static void main(String[] args) {

        // Create list of levels.
        ArrayList<LevelInformation> levels = new ArrayList<>();

        // If there is no arguments, run the Game with the 4 levels one after one.
        if (args.length == 0) {

            // Add the level to the list.
            levels.add(new DirectHitLevel());
            levels.add(new WideEasyLevel());
            levels.add(new Green3Level());
            levels.add(new FinalFourLevel());
        } else { // Add levels depends on the given arguments.

            // Loop through the args.
            for (String arg : args) {
                // Check which level to add.
                if (arg.equals("1")) {
                    levels.add(new DirectHitLevel());
                } else if (arg.equals("2")) {
                    levels.add(new WideEasyLevel());
                } else if (arg.equals("3")) {
                    levels.add(new Green3Level());
                } else if (arg.equals("4")) {
                    levels.add(new FinalFourLevel());
                }
            }
            //Check if there is no valid arguments - if yes- run the 4 levels.
            if (levels.isEmpty()) {
                levels.add(new DirectHitLevel());
                levels.add(new WideEasyLevel());
                levels.add(new Green3Level());
                levels.add(new FinalFourLevel());
            }
        }

        // Run the game
        GUI gui = new GUI("Arknoid Game", 800, 600);

        // Create Animation runner to run all animation.
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();

        // Create the game with the keySensor and the animation runner.
        GameFlow game = new GameFlow(animationRunner, keyboardSensor);

        // Run.
        game.runLevels(levels);
    }
}
