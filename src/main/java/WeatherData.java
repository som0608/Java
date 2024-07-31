/**
 * The {@code WeatherData} class represents weather data and provides methods to extract specific information
 * such as city name, temperature, humidity, wind speed, and weather description.
 */
public class WeatherData {
    private String city;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private String weatherDescription;

    /**
     * Constructs a new WeatherData object with the specified parameters.
     *
     * @param city               The name of the city.
     * @param temperature        The temperature in degrees Celsius.
     * @param humidity           The humidity level as a percentage.
     * @param windSpeed          The wind speed in meters per second.
     * @param weatherDescription The description of the weather.
     */
    public WeatherData(String city, double temperature, double humidity, double windSpeed, String weatherDescription) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherDescription = weatherDescription;
    }

    /**
     * Returns the name of the city.
     *
     * @return The city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the temperature in degrees Celsius.
     *
     * @return The temperature.
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Returns the humidity level as a percentage.
     *
     * @return The humidity.
     */
    public double getHumidity() {
        return humidity;
    }
    
    /**
     * Returns the wind speed in meters per second.
     *
     * @return The wind speed.
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Returns the description of the weather.
     *
     * @return The weather description.
     */
    public String getWeatherDescription() {
        return weatherDescription;
    }
}
