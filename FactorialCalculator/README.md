# Factorial Calculator in Java

This is a simple Java program that calculates the factorial of a number. The number is provided by the user at runtime.

## How it Works

The program uses a recursive method to calculate the factorial of a number. Here's a brief explanation of the code:

1. **Importing the Scanner class**: The `java.util.Scanner` class is used to get user input.

    ```java
    import java.util.Scanner;
    ```

2. **Main method**: This is the entry point for any Java program. The main method runs when you start the program.

    ```java
    public static void main(String[] args) {
        ...
    }
    ```

3. **Scanner object**: Inside the main method, a Scanner object named `scanner` is created to read the user's input.

    ```java
    Scanner scanner = new Scanner(System.in);
    ```

4. **Prompt for user input**: The program prompts the user to enter a number.

    ```java
    System.out.println("Enter a number:");
    ```

5. **Read user input**: The `nextInt` method of the Scanner object is used to read the number entered by the user.

    ```java
    int number = scanner.nextInt();
    ```

6. **Calculate factorial**: The `calculateFactorial` method is called with the number entered by the user. This method returns the factorial of the number.

    ```java
    long factorial = calculateFactorial(number);
    ```

7. **Print the result**: Finally, the program prints the factorial of the number.

    ```java
    System.out.println("The factorial of " + number + " is " + factorial);
    ```

8. **The calculateFactorial method**: This method calculates the factorial of a number using recursion.

    ```java
    public static long calculateFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
    ```

## Running the Program

To run the program, save it to a file called `Factorial.java`. Then, compile it with `javac Factorial.java` and run it with `java Factorial`. The program will prompt you to enter a number, and it will print the factorial of the number you entered to the console.

## Note

Factorials can get large very quickly, so be careful with the input size! For larger inputs, you might want to use a `BigInteger` instead of `long` to store the factorial. But for now, this program should work fine for small inputs.
