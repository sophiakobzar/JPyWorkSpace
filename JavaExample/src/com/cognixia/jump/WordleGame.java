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
        // give them 6 chances to guess the word
        for (int i = 1; i <= 6; i++) {
            System.out.println("Try number: " + i + " out of 6");
        	System.out.println("Enter your guess:" + printLines(secretWord.length()));
            String guess = scanner.nextLine();
            // if guess length doesn't equal word length have them try again
            // let them keep going until they get the correct length 
            while(guess.length() != secretWord.length())
            {
            	System.out.println("Try again the words has to be " + secretWord.length() + 
            									" letters not " + guess.length());
            	guess = "";
                System.out.println("Enter your guess:" + printLines(secretWord.length()));
            	guess = scanner.nextLine();
            }
            // if guess equals word say they won else check letters 
            if (guess.equals(secretWord)) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                scanner.close();
                return;
            } else {
                StringBuilder hint = new StringBuilder();
                // create the hint 
                for (int j = 0; j < secretWord.length(); j++) {
                    // if the letter is in the correct spot label it as green 
                	if (guess.charAt(j) == secretWord.charAt(j)) {
                        hint.append(guess.charAt(j) + "(Green) ");
                    } 
                	// if the word contains the letters but not in right place label yellow.                
                	else if (secretWord.contains(String.valueOf(guess.charAt(j))) && 
                    		checkLetter(secretWord, guess.charAt(j))>= checkLetter(guess, guess.charAt(j))) {
                    	hint.append(guess.charAt(j) + "(Yellow) ");
                    }
                	// letter is not in the word label gray 
                    else {
                        hint.append(guess.charAt(j) + "(Gray) ");
                    }
                }
                // print the hint created above 
                System.out.println(hint);
                System.out.println();
            }
        }
        // let them know the word since they didn't guess it 
        System.out.println("Sorry, you didn't guess the word. The correct word was: " + secretWord);
        scanner.close();
    }
    // checks the amount of times the letter appears in the word
    public int checkLetter(String str, char letter) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }
    // prints the lines. The amount of lines equals amount of letter shown below
    // three <- 5 letters so below returns
    // _ _ _ _ _
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
    	String[] words = {"three", "crimson", "glimmer", 
    						"mystery", "breathe"}; // You can replace/add any word you want
    	int rand_int = rand.nextInt(words.length); // random choice the word in array words
    	WordleGame game = new WordleGame(words[rand_int]); 
        game.playGame();
    }
}