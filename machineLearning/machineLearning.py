import pandas as pd
import numpy as np
from faker import Faker

# Load the dataset
data = pd.read_csv('sampleCsvData.csv')

# Initialize a Faker instance
fake = Faker()

# Define a function to generate a new row
def generate_row():
    account = fake.random_element(elements=data['account'].unique())
    symbol = fake.random_element(elements=data['symbol'].unique())
    quantity = np.random.normal(loc=data['quantity'].mean(), scale=data['quantity'].std())
    price = np.random.normal(loc=data['price'].mean(), scale=data['price'].std())
    return {'account': account, 'symbol': symbol, 'quantity': quantity, 'price': price}

# Generate new data
new_data = pd.DataFrame([generate_row() for _ in range(100)])  # Generate 100 new rows

# Save the new data to a CSV file
new_data.to_csv('newData.csv', index=False)
