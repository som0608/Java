import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;


/**
 * The {@code OpenWeatherMapHandler} class handles API requests and JSON parsing for the OpenWeatherMap API.
 */
public class OpenWeatherMapHandler implements WeatherApiHandler{

    /**
     * Makes an asynchronous API request to the OpenWeatherMap API.
     *
     * @param city    The city for which weather data is requested.
     * @param country The country code for the specified city.
     * @param apiKey  The API key for authenticating the request.
     * @return A CompletableFuture containing the API response as a String.
     */
    @Override
    public CompletableFuture<String> makeApiRequestAsync(String city, String country, String apiKey) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String requestUrl = "https://api.openweathermap.org/data/2.5/weather?APPID=" + apiKey + "&q=" + city + "," + country + "&units=metric";
                URL url = new URL(requestUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    StringBuilder response = new StringBuilder();
                    try (InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                         BufferedReader br = new BufferedReader(isr)) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            response.append(line);
                        }
                    }
                    return response.toString();
                } else {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
        });
    }


    /**
     * Parses the JSON response from the OpenWeatherMap API.
     *
     * @param jsonString The JSON response string.
     * @return A {@code WeatherData} object containing the parsed weather information.
     * @throws IOException If an error occurs during JSON parsing.
     */
    @Override
    public WeatherData parseJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        String city = jsonNode.get("name").asText();
        double temperature = jsonNode.get("main").get("temp").asDouble();
        double humidty = jsonNode.get("main").get("humidity").asDouble();
        double windSpeed = jsonNode.get("wind").get("speed").asDouble();
        String weatherDescription = jsonNode.get("weather").get(0).get("description").asText();

        return new WeatherData(city, temperature, humidty, windSpeed, weatherDescription);
    }
}
