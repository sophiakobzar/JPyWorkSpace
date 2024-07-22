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
            System.out.println("Enter your guess:");
            String guess = scanner.nextLine();
            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                return;
            } else {
                StringBuilder hint = new StringBuilder();

                for (int j = 0; j < secretWord.length(); j++) {
                    if (guess.charAt(j) == secretWord.charAt(j)) {
                        hint.append(guess.charAt(j) + "(Green) ");
                    } else if (secretWord.contains(String.valueOf(guess.charAt(j)))) {
                        hint.append(guess.charAt(j) + "(Yellow) ");
                    } else {
                        hint.append(guess.charAt(j) + "(Gray) ");
                    }
                }

                System.out.println(hint.toString());
            }

        }
        System.out.println("Sorry, you didn't guess the word. The correct word was: " + secretWord);
    }

    public static void main(String[] args) {
        WordleGame game = new WordleGame("apple"); // You can replace "apple" with any word you want
        game.playGame();
    }
}
