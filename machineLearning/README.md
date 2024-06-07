# Python Data Generation Script

This is a Python script that generates synthetic data based on an existing dataset. The script uses the `pandas`, `numpy`, and `faker` libraries to generate new data that follows the distribution of the original data.

## Script Details

The script performs the following steps:

1. Loads an existing dataset from a CSV file.
2. Initializes a Faker instance for generating synthetic data.
3. Defines a function to generate a new row of data. This function selects random account and symbol values from the original data, and generates quantity and price values based on the mean and standard deviation of these fields in the original data.
4. Generates 100 new rows of data using the defined function.
5. Saves the new data to a new CSV file.

## How to Run

1. Ensure you have Python installed on your system.
2. Install the required libraries using pip:
    pip install pandas numpy faker

3. Run the script using Python:
Replace `machinelearning.py` with the name of your Python script.

