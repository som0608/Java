import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The {@code WeatherAppGUI} class provides a graphical user interface for the weather application.
 */
public class WeatherAppGUI {
    /**
     * Creates and displays the main GUI for the weather application.
     */
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel(new FlowLayout());

        JTextField cityField = new JTextField(15);
        JTextField countryField = new JTextField(15);
        
        String[] apiOptions = {"OpenWeatherMap", "WeatherBit"};
        JComboBox<String> apiComboBox = new JComboBox<>(apiOptions);

        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                String country = countryField.getText();
                String selectedAPI = (String) apiComboBox.getSelectedItem();

                GetWeatherData getWeatherData = new GetWeatherData(city, country, selectedAPI);
                try {
                    getWeatherData.requestWeatherData();

                    if (getWeatherData.hasError()) {
                        JOptionPane.showMessageDialog(frame, "Error: " + getWeatherData.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String cityName = getWeatherData.weatherCity;
                        String weatherDescription = getWeatherData.weatherDescription;
                        double tempreture = getWeatherData.tempreture;
                        double humidity = getWeatherData.humidity;
                        double windSpeed = getWeatherData.windSpeed;

                        WeatherDataDialog.showWeatherDataDialog(cityName, weatherDescription, tempreture, humidity, windSpeed);
                    }

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, "Error: IOException", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(new JLabel("City: "));
        panel.add(cityField);
        panel.add(new JLabel("Country code: "));
        panel.add(countryField);
        panel.add(new JLabel("Weather API: "));
        panel.add(apiComboBox);
        panel.add(searchButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
