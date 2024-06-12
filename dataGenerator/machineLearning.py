import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression

def generate_new_data(data, amount_of_data):
    # Convert the 'account' and 'symbol' columns to categorical and save the mappings
    data['account'], account_mapping = data['account'].astype('category').cat.codes, data['account'].astype('category')
    data['symbol'], symbol_mapping = data['symbol'].astype('category').cat.codes, data['symbol'].astype('category')

    # Define the model
    model = LinearRegression()

    # Train the model using the original 'quantity' values
    model.fit(data[['account', 'symbol']], data['quantity'])

    # Generate new data
    new_data = pd.DataFrame()
    new_data['account'] = np.random.choice(data['account'], size=amount_of_data)
    new_data['symbol'] = np.random.choice(data['symbol'], size=amount_of_data)

    # Predict the 'quantity' values for the new data
    new_data['quantity'] = model.predict(new_data)

    # Ensure the 'quantity' values are within the same range as the original data
    min_quantity = data['quantity'].min()
    max_quantity = data['quantity'].max()
    new_data['quantity'] = np.clip(new_data['quantity'], min_quantity, max_quantity)

    # Map the generated 'account' and 'symbol' values back to their original string values
    new_data['account'] = account_mapping.cat.categories[new_data['account']]
    new_data['symbol'] = symbol_mapping.cat.categories[new_data['symbol']]

    return new_data

# Load the dataset
data = pd.read_csv('sampleCsvData.csv')

# Set the amount of data to generate
amount_of_data = 200

# Generate the new data
new_data = generate_new_data(data, amount_of_data)

# Save the new data to a CSV file
new_data.to_csv('newData.csv', index=False)
