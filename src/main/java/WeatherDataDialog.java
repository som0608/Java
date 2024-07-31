import javax.swing.*;

/**
 * The {@code WeatherDataDialog} class provides a method to display weather information in a dialog.
 */
public class WeatherDataDialog {

    /**
     * Displays a dialog with weather information.
     *
     * @param cityName           The name of the city.
     * @param weatherDescription The description of the weather.
     * @param temperature        The temperature in degrees Celsius.
     * @param humidity           The humidity percentage.
     * @param windSpeed          The wind speed in meters per second.
     */
    
    public static void showWeatherDataDialog(String cityName, String weatherDescription, double temperature, double humidity, double windSpeed) {
        StringBuilder message = new StringBuilder();
        message.append("Weather information").append("\n");
        message.append("City Name: ").append(cityName).append("\n");
        message.append("Weather Description: ").append(weatherDescription).append("\n");
        message.append("Temperature: ").append(temperature).append("°C\n");
        message.append("Humidity: ").append(humidity).append("%\n");
        message.append("Wind Speed: ").append(windSpeed).append("m/s");

        JOptionPane.showMessageDialog(null, message.toString(), "Weather Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
