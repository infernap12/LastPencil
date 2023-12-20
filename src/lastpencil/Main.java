package lastpencil;

import java.util.Scanner;

public class Main {

    private static final String NAME1 = "Liam";
    private static final String NAME2 = "Emma";
    private static String first;
    private static int pencils;



    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        pencils = promptStartPencils("How many pencils would you like to use:");
        first = whosOnFirst("Who will be the first (%s, %s):".formatted(NAME1,NAME2));
        String player = first;
        while (pencils > 0) {
            drawPencils();
            System.out.printf("%s's turn!", player);
            System.out.println();
            pencils -= playPencils();
            player = player.equals(NAME1) ? NAME2 : NAME1; //swap players
        }
        System.out.println(player + " won!");
    }

    private static int playPencils() {
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
    private static int promptStartPencils(String prompt) {
        System.out.println(prompt);
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
}
