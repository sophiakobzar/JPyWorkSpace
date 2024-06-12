# Project Setup Instructions

Follow these steps to run the `machineLearning.py` script:

1. **Change the current working directory** to the project directory:
    ```
    cd <project-directory>
    ```
    Replace `<project-directory>` with the path to your project directory.

2. **Run the script** using Python:
    ```
    python machineLearning.py
    ```
    This command runs the `machineLearning.py` script.

3. If you encounter an error stating that a module is missing, you will need to install it. You can do this using pip, Python's package installer. Run the following command:
    ```
    pip install <moduleName>
    ```
    Replace `<moduleName>` with the name of the missing module.

Please ensure that you have the necessary permissions to install packages on your system.

Here’s a brief explanation of what each part of the script does:

The LinearRegression() function defines a linear regression model.

The model.fit() function trains the model on your original data.

The np.random.choice() function is used to generate random ‘account’ and ‘symbol’ values based on your original data.

The model.predict() function uses the trained model to predict ‘quantity’ values based on the generated ‘account’ and ‘symbol’ values.

The account_mapping.cat.categories[] and symbol_mapping.cat.categories[] lines map the generated ‘account’ and ‘symbol’ values back to their original string values.

The scaler.inverse_transform() function scales the ‘quantity’ values back to the original range.

The new_data.to_csv() function saves the generated data to a CSV file.
