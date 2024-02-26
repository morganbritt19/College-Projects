# This project was designed to familiarize me with using APIs, and is a proof of concept to get data from weather.gov. 

import requests

def fetch_data():
    # ***change*** Fetch the forecast URL from the API response
    url = "https://api.weather.gov/points/[lat],[long]"
    response = requests.get(url)
    # Raise an error if request failed
    response.raise_for_status()
    data = response.json()
    
    # Extract the forecast URL
    forecast_url = data["properties"]["forecast"]
    
    # Fetch the forecast data
    response = requests.get(forecast_url)
    response.raise_for_status()
    forecast_data = response.json()
    return forecast_data

# This function displays the weather data pulled from the weather.gov API
def display_data(weather):
    for i in range(13):
        # ***extract*** the name, temperature, and detailedForecast data
        period_data = weather['properties']['periods'][i]
        name = period_data['name']
        temperature = period_data['temperature']
        detailed_forecast = period_data['detailedForecast']

        # ***print*** the name, temperature, and forecast
        print(f"Period {i}:")
        print(f"Name: {name}")
        print(f"Temperature: {temperature} degrees F")
        print(f"Detailed Forecast: {detailed_forecast}\n")

# This is the main function
def main():
    try:
        weather = fetch_data()
        display_data(weather)
    except requests.RequestException as e:
        print(f"An error occurred: {e}")

# This is the dunder function (double underscore function) that calls main.
if __name__ == "__main__":
    main()
