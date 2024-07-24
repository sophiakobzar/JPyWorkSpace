package com.cognixia.jump;
import java.util.Scanner;

public class WordleGame {
    private final String secretWord;

    public WordleGame(String secretWord) {
        this.secretWord = secretWord;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 6; i++) {
            boolean[] found = new boolean[26];  // Reset the found array for each guess

            System.out.println("Enter your guess:");
            String guess = scanner.nextLine();
            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                return;
            } else {
                StringBuilder hint = new StringBuilder();

                for (int j = 0; j < guess.length(); j++) {
                    if (j < secretWord.length() && secretWord.charAt(j) == guess.charAt(j)) {
                        hint.append(guess.charAt(j) + "(Green) ");
                        found[guess.charAt(j) - 'a'] = true;  // Mark the character as found
                    } else if (j < secretWord.length() && !found[guess.charAt(j) - 'a'] && secretWord.contains(String.valueOf(guess.charAt(j)))) {
                        hint.append(guess.charAt(j) + "(Yellow) ");
                        found[guess.charAt(j) - 'a'] = true;  // Mark the character as found
                    } else {
                        hint.append(guess.charAt(j) + "(Gray) ");
                    }
                }

                System.out.println(hint);
            }
        }
        System.out.println("Sorry, you didn't guess the word. The correct word was: " + secretWord);
    }



    public static boolean foundAllLetter(String secretWord, String guess, char letter) {
        int countInSecret = 0;
        int countInGuess = 0;

        // Count occurrences of the letter in the secret word
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                countInSecret++;
            }
        }
        // Count occurrences of the letter in the guess
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == letter) {
                countInGuess++;
            }
        }
        // Return true if the counts match, false otherwise
        return countInSecret == countInGuess;
    }

    public static void main(String[] args) {
        WordleGame game = new WordleGame("apple"); // You can replace "apple" with any word you want
        game.playGame();
    }
}
