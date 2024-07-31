import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for handling weather API requests and responses.
 */
public interface WeatherApiHandler {
    /**
     * Makes an asynchronous API request to the weather service.
     *
     * @param city The city for which weather data is requested.
     * @param country The country code for the specified city.
     * @param apiKey The API key for the weather service.
     * @return A CompletableFuture containing the API response.
     */
    CompletableFuture<String> makeApiRequestAsync(String city, String country, String apiKey);

    /**
     * Parses the JSON response from the weather API.
     *
     * @param jsonString The JSON response string.
     * @return A WeatherData object containing parsed weather information.
     * @throws IOException If an error occurs during JSON parsing.
     */
    WeatherData parseJson(String jsonString) throws IOException;
}