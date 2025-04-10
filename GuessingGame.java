package projects;

import java.util.*;

    public class GuessingGame {
        static Scanner scanner = new Scanner(System.in);
        static Random random = new Random();

        static class Player {
            String name;
            int score = 100;
            int attempts = 0;

            Player(String name) {
                this.name = name;
            }

            void reset() {
                this.score = 100;
                this.attempts = 0;
            }
        }

        public static void main(String[] args) {
            System.out.println("Welcome to the Multiplayer Number Guessing Game!");

            boolean playAgain;
            do {
                System.out.print("Enter number of players: ");
                int playerCount = scanner.nextInt();
                scanner.nextLine();

                Player[] players = new Player[playerCount];
                for (int i = 0; i < playerCount; i++) {
                    System.out.print("Enter name for Player " + (i + 1) + ": ");
                    players[i] = new Player(scanner.nextLine());
                }

                int maxNumber = chooseDifficulty();
                int target = random.nextInt(maxNumber) + 1;
                boolean guessedCorrectly = false;

                while (!guessedCorrectly) {
                    for (Player player : players) {
                        System.out.println("\n" + player.name + "'s turn:");
                        System.out.print("Enter your guess: ");
                        int guess = scanner.nextInt();
                        player.attempts++;

                        // Default hints to the User
                        int mid = maxNumber / 2;
                        if (guess <= mid) {
                            System.out.println("Hint: The number is in the " + (target <= mid ? "lower" : "upper") + " half of the range.");
                        } else {
                            System.out.println("Hint: The number is in the " + (target <= mid ? "lower" : "upper") + " half of the range.");
                        }

                        if (target % 2 == 0) {
                            System.out.println("Hint: The number is even.");
                        } else {
                            System.out.println("Hint: The number is odd.");
                        }

                        if (guess == target) {
                            System.out.println(" " + player.name + " guessed the number " + target + " correctly in " + player.attempts + " attempts!");
                            guessedCorrectly = true;
                            break;
                        } else {
                            player.score -= 5;
                            System.out.println("Incorrect guess. -5 points.");

                            System.out.print("Do you want to use a paid hint for 10 points? (yes/no): ");
                            String useHint = scanner.next();
                            if (useHint.equalsIgnoreCase("yes") && player.score >= 10) {
                                player.score -= 10;
                                if (target > guess) {
                                    System.out.println("Paid Hint: Try a higher number.");
                                } else {
                                    System.out.println("Paid Hint: Try a lower number.");
                                }
                            }
                            System.out.println("Current score: " + player.score);
                        }
                    }
                }

                // Showing final stats
                System.out.println("\n Game Over! Final Stats:");
                for (Player player : players) {
                    System.out.println(player.name + " - Score: " + player.score + ", Attempts: " + player.attempts);
                    player.reset(); // Reset if playing again
                }

                // Play again prompt
                System.out.print("\n Do you want to play again? (yes/no): ");
                String response = scanner.next();
                playAgain = response.equalsIgnoreCase("yes");

            } while (playAgain);

            System.out.println("Thanks for playing! Goodbye!");
        }

        static int chooseDifficulty() {
            System.out.println("\n Select Difficulty Level:");
            System.out.println("1. Easy (1-50)");
            System.out.println("2. Medium (1-100)");
            System.out.println("3. Hard (1-200)");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return 50;
                case 2:
                    return 100;
                case 3:
                    return 200;
                default:
                    System.out.println("Invalid choice. Defaulting to Medium.");
                    return 100;
            }
        }
    }

