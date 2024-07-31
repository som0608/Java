import java.io.*;
import javax.swing.*;

/**
 * The {@code Main} class serves as the entry point for the Weather App.
 * It invokes the GUI creation and display using SwingUtilities.
 */
public class Main {
    /**
     * The main method to start the Weather App.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGUI.createAndShowGUI();
        });
    }
}
