import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give a number and I will give the factorial of that number");
        System.out.println("Example: 3! = 1 + 2 + 3 = 6");

        System.out.print("\nEnter a number: ");
        int number = scanner.nextInt();
        long factorial = calculateFactorial(number);
        System.out.println("The factorial of " + number + " is " + factorial);
    }

    public static long calculateFactorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }
}
