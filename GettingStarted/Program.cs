using System;
using System.Diagnostics;

class Program
{
    static void Main(string[] args)
    {
        int[] arr = new int[10]; // Initialize an array of 10 integers
        Random randNum = new Random(); // Instantiate a new random number generator

        // Populate the array with ten random numbers between 1 and 100
        for(int i = 0; i < arr.Length; i++)
        {
            arr[i] = randNum.Next(1, 100); // Generate a random number and assign it to the current array element
        }

        Console.WriteLine("Unsorted array:");
        PrintArray(arr); // Display the unsorted array

        Stopwatch stopwatch = new Stopwatch();
        stopwatch.Start(); // Start the stopwatch

        BubbleSort(arr); // Sort the array using the Bubble Sort algorithm

        stopwatch.Stop(); // Stop the stopwatch
        Console.WriteLine($"Time taken to Bubble sort: {stopwatch.Elapsed} ms"); // Print the elapsed time in milliseconds

        Console.WriteLine("Sorted array:");
        PrintArray(arr); // Display the sorted array
    }

    static void BubbleSort(int[] arr)
    {
        int n = arr.Length; // Get the length of the array

        // Outer loop - each pass through the array will bubble up the largest unsorted element to its correct position
        for (int i = 0; i < n - 1; i++) 
        {
            // Inner loop - perform pairwise comparisons and swaps
            for (int j = 0; j < n - i - 1; j++)
            {
                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j + 1])
                {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j]; // Store the current element in a temporary variable
                    arr[j] = arr[j + 1]; // Replace the current element with the next element
                    arr[j + 1] = temp; // Replace the next element with the value of the temporary variable (old current element)
                }
            }
        }
    }

    static void PrintArray(int[] arr)
    {
        int n = arr.Length; // Get the length of the array

        // Iterate through the array and print each element
        for (int i = 0; i < n; ++i)
            Console.Write(arr[i] + " ");
        Console.WriteLine(); // Print a new line after printing all elements
    }
}
