import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The {@code GetWeatherData} class handles the retrieval of weather data based on user input and selected API.
 */
public class GetWeatherData {
    private String city;
    private String country;
    private String apiType;
    private boolean errorFlag;
    private String errorMessage;

    public String weatherCity;
    public String weatherDescription;
    public double tempreture;
    public double humidity;
    public double windSpeed;

    /**
     * Constructs a new {@code GetWeatherData} instance with the specified parameters.
     *
     * @param city    The city for which weather data is requested.
     * @param country The country code for the specified city.
     * @param apiType The selected weather API type (e.g., "OpenWeatherMap" or "WeatherBit").
     */
    public GetWeatherData(String city, String country, String apiType) {
        this.city = city;
        this.country = country;
        this.apiType = apiType;
        this.errorFlag = false;
    }

    /**
     * Requests weather data asynchronously from the specified weather API.
     *
     * @throws IOException If an error occurs during the API request.
     */
    public void requestWeatherData() throws IOException {
        WeatherApiClient weatherApiClient = new WeatherApiClient(apiType);

            if (isValidInput(city, country)) {
                CompletableFuture<String> requestFuture = weatherApiClient.makeApiRequestAsync(city, country);
                try {
                    String response = requestFuture.get();

                    if (response != null && !response.contains("null")) {
                        WeatherData weatherData = weatherApiClient.parseJson(response);

                        this.weatherCity = weatherData.getCity();
                        this.weatherDescription = weatherData.getWeatherDescription();
                        this.tempreture = weatherData.getTemperature();
                        this.humidity = weatherData.getHumidity();
                        this.windSpeed = weatherData.getWindSpeed();
                    } else {
                        this.errorFlag = true;
                        this.errorMessage = "An error occurred during the API request.";
                    }
                } catch (InterruptedException | ExecutionException e) {
                    this.errorFlag = true;
                    this.errorMessage = "InterruptedException or ExecutionException";
                }
            } else {
                this.errorFlag = true;
                this.errorMessage = errorMessage + "\n" + "Please enter valid city and country code.";
            }
    }

    /**
     * Checks if the provided city and country are valid.
     *
     * @param city    The city to validate.
     * @param country The country code to validate.
     * @return {@code true} if the input is valid, {@code false} otherwise.
     */
    private boolean isValidInput(String city, String country) {
        if (city.isEmpty() || !city.matches("[a-zA-Z]+")) {
            this.errorFlag = true;
            this.errorMessage = "Invalid city name.";
            return false;
        }
        if (country.isEmpty() || !country.matches("[a-zA-Z]+")) {
            this.errorFlag = true;
            this.errorMessage = "Invalid country code.";
            return false;
        }
        return true;
    }

    /**
     * Checks if an error occurred during the weather data retrieval.
     *
     * @return {@code true} if an error occurred, {@code false} otherwise.
     */
    public boolean hasError() {
        return errorFlag;
    }

    /**
     * Gets the error message if an error occurred during weather data retrieval.
     *
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
