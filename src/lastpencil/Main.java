package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String NAME1 = "Liam";
    private static final String NAME2 = "Emma";
    private static int pencils;


    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        pencils = promptStartPencils();
        String player = whosOnFirst("Who will be the first (%s, %s):".formatted(NAME1, NAME2));
        while (pencils > 0) {
            drawPencils();
            System.out.printf("%s's turn!", player);
            System.out.println();
            pencils -= player.equals(NAME1) ? getUserMove() : Bot.getBotMove();
            player = player.equals(NAME1) ? NAME2 : NAME1; //swap players, useful on last line, as after someone takes the last, it swaps to print the victors name
        }
        System.out.println(player + " won!");
    }

    private static int getUserMove() {
        while (true) {
            try {
                int move = Integer.parseInt(scanner.nextLine());
                if (move < 1 || move > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                } else if (move > pencils) {
                    System.out.println("Too many pencils were taken.");
                } else {
                    return move;
                }
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
    }

    private static void drawPencils() {
        for (int i = 0; i < pencils; i++) {
            System.out.print("|");
        }
        System.out.println();
    }

    private static String whosOnFirst(String prompt) {
        while (true) {
            System.out.println(prompt);
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase(NAME1) || name.equalsIgnoreCase(NAME2)) {
                return name;
            } else {
                System.out.println("Choose between " + NAME1 + " and " + NAME2);
            }
        }
    }

    private static int promptStartPencils() {
        System.out.println("How many pencils would you like to use:");
        while (true) {
            String input = scanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    if (value == 0) {
                        System.out.println("The number of pencils should be positive");
                    } else {
                        System.out.println("The number of pencils should be numeric");
                    }
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
    }
    static class Bot {
        private static final Random random = new Random();

        /**
         * <p>Determines the next move of the bot.</p>
         * <p>The strategy is based on the current number of pencils left.</p>
         * <p></p>
         * <p>The method calculates the game state by adding 3 to the number of pencils and
         * then taking the modulo 4 of this sum. This calculation adjusts the strategy
         * to ensure the bot ends up in a winning position when possible.</p>
         * <p></p>
         * <p>If the calculated state is 0, indicating a non-winning position, the bot
         * randomly chooses to take 1 to 3 pencils, unless there is only one pencil left,
         * in which case it takes the last pencil.</p>
         * <p></p>
         * <p>If the state is any other number (1, 2, or 3), the bot takes that number of pencils,
         * aiming to leave a multiple of 4 pencils for the opponent, thereby forcing the opponent
         * into a non-winning position.</p>
         * <p></p>
         * <p>After calculating the move, it is printed to the console, and then returned.</p>
         *
         * @return an integer representing the bot's move, ranging from 1 to 3.
         */
        public static int getBotMove() {
            int move;
            int state = (pencils + 3) % 4;
            if (state == 0) {
                move = pencils == 1 ? 1 : random.nextInt(2) + 1;
            } else {
                move = state;
            }
            System.out.println(move);
            return move;
        }


    }
}