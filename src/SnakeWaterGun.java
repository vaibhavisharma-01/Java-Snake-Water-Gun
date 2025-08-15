import java.util.Random;
import java.util.Scanner;

public class SnakeWaterGun {
    private static final String[] OPTIONS = {"Snake", "Water", "Gun"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("=======================================");
        System.out.println("      Snake • Water • Gun  (Java)      ");
        System.out.println("=======================================");
        System.out.println("Rules:");
        System.out.println("- Snake drinks Water");
        System.out.println("- Water sinks Gun");
        System.out.println("- Gun kills Snake");
        System.out.println();
        System.out.println("Type S for Snake, W for Water, G for Gun. Type Q to quit.");
        System.out.println();

        int rounds = askRounds(sc);
        int userScore = 0, computerScore = 0, draws = 0;

        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round + " of " + rounds);
            String userChoice = getUserChoice(sc);
            if (userChoice == null) {
                System.out.println("Exiting game. Thanks for playing!");
                break;
            }

            String computerChoice = OPTIONS[rand.nextInt(3)];
            System.out.println("You chose: " + userChoice);
            System.out.println("Computer chose: " + computerChoice);

            int result = decide(userChoice, computerChoice);
            if (result == 1) {
                System.out.println("You win this round!");
                userScore++;
            } else if (result == -1) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("It's a draw.");
                draws++;
            }
        }

        System.out.println("\n========== Final Score ==========");
        System.out.println("You: " + userScore + " | Computer: " + computerScore + " | Draws: " + draws);
        if (userScore > computerScore) {
            System.out.println("🎉 Congratulations! You win the game!");
        } else if (computerScore > userScore) {
            System.out.println("💻 Computer wins the game!");
        } else {
            System.out.println("It's a tie overall!");
        }
        sc.close();
    }

    private static int askRounds(Scanner sc) {
        System.out.print("Enter number of rounds (default 5): ");
        String line = sc.nextLine().trim();
        try {
            int n = Integer.parseInt(line);
            if (n <= 0) throw new NumberFormatException();
            return n;
        } catch (Exception e) {
            return 5;
        }
    }

    private static String getUserChoice(Scanner sc) {
        while (true) {
            System.out.print("Your choice [S/W/G or Q to quit]: ");
            String in = sc.nextLine().trim().toLowerCase();
            if (in.equals("q")) return null;
            if (in.equals("s") || in.equals("snake") || in.equals("1")) return "Snake";
            if (in.equals("w") || in.equals("water") || in.equals("2")) return "Water";
            if (in.equals("g") || in.equals("gun") || in.equals("3")) return "Gun";
            System.out.println("Invalid input. Please type S, W, G or Q.");
        }
    }

    private static int decide(String user, String comp) {
        if (user.equals(comp)) return 0;
        switch (user) {
            case "Snake": return comp.equals("Water") ? 1 : -1;
            case "Water": return comp.equals("Gun") ? 1 : -1;
            case "Gun": return comp.equals("Snake") ? 1 : -1;
        }
        return 0;
    }
}
