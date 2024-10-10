package com.cognixia.jump;
import java.util.Scanner;
import java.util.Random;

public class WordleGame {
    private final String secretWord;
    public WordleGame(String secretWord) {
        this.secretWord = secretWord;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 6; i++) {
            System.out.println("Try number: " + i + " out of 6");
        	System.out.println("Enter your guess:" + printLines(secretWord.length()));
            String guess = scanner.nextLine();
            while(guess.length() != secretWord.length())
            {
            	System.out.println("Try again the words has to be " + secretWord.length() + 
            									" letters not " + guess.length());
            	guess = "";
                System.out.println("Enter your guess:" + printLines(secretWord.length()));
            	guess = scanner.nextLine();
            }            
            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                scanner.close();
                return;
            } else {
                StringBuilder hint = new StringBuilder();

                for (int j = 0; j < secretWord.length(); j++) {
                    if (guess.charAt(j) == secretWord.charAt(j)) {
                        hint.append(guess.charAt(j) + "(Green) ");
                    } else if (secretWord.contains(String.valueOf(guess.charAt(j))) && 
                    		checkLetter(secretWord, guess.charAt(j))>= checkLetter(guess, guess.charAt(j))) {
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
    public StringBuilder printLines(int amountOfLines) {
        StringBuilder lines = new StringBuilder();

        for(int i = 0; i<amountOfLines; i++)
        {
    		lines.append(" _");
        }
        return lines;
    }

    public static void main(String[] args) {
    	Random rand = new Random();
    	String[] words = {"three", "crimson", "glimmer", "mystery", "breathe"};
    	int rand_int = rand.nextInt(words.length);
    	WordleGame game = new WordleGame(words[rand_int]); // You can replace with any word you want
        game.playGame();
    }
}