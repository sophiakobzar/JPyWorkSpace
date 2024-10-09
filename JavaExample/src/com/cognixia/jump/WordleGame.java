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
            System.out.println("Enter your guess: _ _ _ _ _");
            String guess = scanner.nextLine();
            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                scanner.close();
                return;
            } else {
                StringBuilder hint = new StringBuilder();

                for (int j = 0; j < secretWord.length(); j++) {
                    if (guess.charAt(j) == secretWord.charAt(j)) {
                        hint.append(guess.charAt(j) + "(Green) ");
                    } else if (secretWord.contains(String.valueOf(guess.charAt(j))) && checkLetter(secretWord, guess.charAt(j))>= checkLetter(guess, guess.charAt(j))) {
                       hint.append(guess.charAt(j) + "(Yellow) ");
                    }
                    else {
                        hint.append(guess.charAt(j) + "(Gray) ");
                    }
                }
                System.out.println(hint);
                System.out.println();
            }

        }
        System.out.println("Sorry, you didn't guess the word. The correct word was: " + secretWord);
        scanner.close();
    }
    public int checkLetter(String str, char letter) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        WordleGame game = new WordleGame("three"); // You can replace "apple" with any word you want
        game.playGame();
    }
}