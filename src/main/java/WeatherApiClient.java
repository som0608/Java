import java.io.*;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The {@code WeatherApiClient} class provides methods to make asynchronous API requests and retrieve API keys.
 * This class dynamically selects the appropriate API handler based on the specified API type.
 */
public class WeatherApiClient{
    
    private WeatherApiHandler handler;
    private String apiKey;

    /**
     * Constructs a {@code WeatherApiClient} with the specified API type.
     *
     * @param apiType The type of weather API (e.g., "OpenWeatherMap" or "WeatherBit").
     */
    public WeatherApiClient(String apiType) {
        if (apiType.equals("OpenWeatherMap")) {
            handler = new OpenWeatherMapHandler();
        } else {
            handler = new WeatherBitHandler();
        }
    }    

    /**
     * Makes an asynchronous API request with the specified city and country.
     *
     * @param city    The city for which weather data is requested.
     * @param country The country code for the specified city.
     * @return A CompletableFuture containing the API response.
     */
    public CompletableFuture<String> makeApiRequestAsync(String city, String country) {
        CompletableFuture<String> apiKeyFuture = getApiKeyAsync();
        try {
            apiKey = apiKeyFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return handler.makeApiRequestAsync(city, country, apiKey);
    }
    

    /**
     * Parses the JSON response string.
     *
     * @param jsonString The JSON response string.
     * @return A WeatherData object containing parsed weather information.
     * @throws IOException If an error occurs during JSON parsing.
     */
    public WeatherData parseJson(String jsonString) throws IOException {
        return handler.parseJson(jsonString);
    }

    /**
     * Retrieves the API key asynchronously based on the handler's class name.
     *
     * @return A CompletableFuture containing the API key.
     */
    public CompletableFuture<String> getApiKeyAsync() {
        return CompletableFuture.supplyAsync(() -> {
            Properties properties = new Properties();
            try (InputStream input = WeatherApiClient.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    return null;
                }
                properties.load(input);
            } catch (IOException e) {
                return null;
            }
            return properties.getProperty(handler.getClass().getSimpleName() + ".key");
        });
    }
}