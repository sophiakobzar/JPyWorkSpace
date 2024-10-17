import pandas as pd
import numpy as np
from faker import Faker
from sklearn.mixture import GaussianMixture

# Load the dataset
data = pd.read_csv('sampleCsvData.csv')

# Initialize a Faker instance
fake = Faker()

# Fit a Gaussian Mixture Model to the original data
gmm = GaussianMixture(n_components=3, random_state=0)
gmm.fit(data[['quantity', 'price']])

# Define a function to generate a new row
def generate_row():
    account = fake.random_element(elements=data['account'].unique())
    symbol = fake.random_element(elements=data['symbol'].unique())
    quantity, price = gmm.sample()
    # Scale the quantity value
    quantity = quantity / 1000  # Adjust the scaling factor as needed
    return {'account': account, 'symbol': symbol, 'quantity': quantity, 'price': price}

# Generate new data
new_data = pd.DataFrame([generate_row() for _ in range(100)])  # Generate 100 new rows

# Save the new data to a CSV file
new_data.to_csv('newData.csv', index=False)