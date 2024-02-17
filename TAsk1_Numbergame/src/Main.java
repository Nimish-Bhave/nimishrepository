import java.util.Scanner;
import java.util.Random;
 class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int randomNumber;
        int userGuess;
        int attempts = 0;
        int roundsWon = 0;

        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("I've selected a number between 1 and 100. Try to guess it!");

            while (true) {
                System.out.print("Enter your guess: ");
                userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number " + randomNumber + " correctly!");
                    roundsWon++;
                    break;
                }
            }

            System.out.println("Attempts taken: " + attempts);
            System.out.println("Rounds won: " + roundsWon);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }

            attempts = 0;
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
