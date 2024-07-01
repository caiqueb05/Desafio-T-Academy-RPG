package Combat;

import Characters.Archer;
import Characters.Character;
import Characters.Enemy.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

//    static Goblin goblin;

    static {
//        Class<Goblin> goblinClass = Goblin.class;
//        goblin = new Goblin();
        Goblin goblin = new Goblin();
        Ghost ghost = new Ghost();
        Skeleton skeleton = new Skeleton();
    }

    public void showOptions() {
        boolean isValidOption = false;
        while (!isValidOption) {
            System.out.println("=============================================================================================");
            System.out.println("=====================================Choose your option!=====================================");
            System.out.println("=============================================================================================");
            System.out.println("1 - Choose an already created character");
            System.out.println("2 - Create a new character");
            System.out.println("3 - Start a battle");
            System.out.println("4 - View enemies");
            System.out.println("5 - Quit the game");
            System.out.println("=============================================================================================");

            try {

                Enemy selectedEnemy = null;
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        // Code to choose an already created character
                        isValidOption = true;
                        Character.selectCharacter();
                        break;
                    case 2:
                        // Code to create a new character
                        isValidOption = true;
                        Character.createCharacter();
                        break;
                    case 3:
                        // Code to create a new character

                        selectedEnemy = Battle.selectRandomEnemy();
                        if (selectedEnemy != null) {
                            Battle.startBattle(selectedEnemy);
                        }
                        isValidOption = true;
                        break;
                    case 4:
                        // Code to create a new character
                        isValidOption = true;
                        Enemy.displayEnemies();
                        break;
                    case 5:
                        // Code to quite the game
                        isValidOption = true;
                        endSummary();
                        System.exit(0); // Terminate the program
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1 or 2.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Invalid option. Please choose 1 or 2.");
                scanner.next();
            }
        }
    }


    public void endSummary() {
        System.out.println("=============================================================================================");
        System.out.println("=======================================Thanks for playing!=====================================");
        System.out.println("=============================================================================================");
    }
}
