package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Defining a Settings class to control the game's setting.
 */
public class Settings implements Initializable {

    @FXML
    Button saveButton;
    @FXML
    Button cancelButton;
    @FXML
    ColorPicker colorPicker1;
    @FXML
    ColorPicker colorPicker2;
    @FXML
    ChoiceBox<Integer> cbSize;
    @FXML
    ToggleButton startingPlayer1;
    @FXML
    ToggleButton startingPlayer2;
    @FXML
    Rectangle rect1;
    @FXML
    Rectangle rect2;

    private String[] settings = null;
    private final static String fileName = "othelloApp/gameSettings";

    /**
     * The Settings default constructor.
     */
    public Settings() {}

    /**
     * The Settings constructor.
     * @param settings The given settings.
     */
    private Settings(String[] settings) {
        this.settings = settings;
    }

    /**
     * The method initializes the Settings to be th controller for settings FXML.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateSelf();
        cbSize.setValue(getSize());
        Color color1 = parseToColor(settings[2]);
        Color color2 = parseToColor(settings[3]);
        colorPicker1.setValue(color1);
        colorPicker2.setValue(color2);
        rect1.setFill(color1);
        rect2.setFill(color2);
        setToggleButtonColor();
    }

    /**
     * The method sets the toggle group button colors.
     */
    private void setToggleButtonColor() {
        Color color1 = colorPicker1.getValue();
        Color color2 = colorPicker2.getValue();
        String stringColor1 = "-fx-color: rgb(" + color1.getRed()*255 + ", "
                + color1.getGreen()*255 + ", " + color1.getBlue()*255 + ")";
        String stringColor2 = "-fx-color: rgb(" + color2.getRed()*255 + ", "
                + color2.getGreen()*255 + ", " + color2.getBlue()*255 + ")";
        startingPlayer1.setStyle(stringColor1);
        startingPlayer2.setStyle(stringColor2);
    }

    /**
     * The method updates it's settings from file.
     */
    private void updateSelf() {
        this.settings = loadSettings().settings;
    }

    /**
     * The method returns the board size saved in the settings.
     * @return The pref board size.
     */
    public int getSize() { return Integer.parseInt(settings[0]); }

    /**
     * The method returns the starting player.
     * @return The symbol of the starting player.
     */
    public String getFirstPlayer() { return settings[1]; }

    /**
     * The method returns the first player's color.
     * @return
     */
    public Color getColorX() { return parseToColor(settings[2]); }

    /**
     * The method returns the second player's color.
     * @return The seconds player's color.
     */
    public Color getColorY() { return parseToColor(settings[3]); }

    /**
     * The static constructor for the Settings class.
     * @return The loaded settings.
     */
    public static Settings loadSettings() {
        try {
            String settings[] = new String[4];
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            for (int i = 0; i < settings.length; i++) { settings[i] = bufferedReader.readLine(); }
            bufferedReader.close();
            if (settings[0].equals("")) return defaultSettings();
            return new Settings(settings);
        } catch (FileNotFoundException ex) { System.out.println("Unable to open settings");
        } catch (IOException ex) { System.out.println("Error reading settings"); }

        return defaultSettings();
    }

    /**
     * The method parses a string to a color and returns it.
     * @param color The string representation of the color.
     * @return The new color.
     */
    private static Color parseToColor(String color) {
        String [] rgb = color.split(" ");
        double r =Double.parseDouble(rgb[0]);
        double g = Double.parseDouble(rgb[1]);
        double b = Double.parseDouble(rgb[2]);
        return Color.color(r, g, b);
    }

    /**
     * The static constructor for default settings.
     * @return The settings created.
     */
    public static Settings defaultSettings() {
        String[] settings = new String[4];
        settings[0] = "8";
        settings[1] = "X";
        settings[2] = "0.1 0.0 0.0";
        settings[3] = "0.0 0.1 0.0";
        return new Settings(settings);
    }

    /**
     * The method sets the board size in setting, based on FXML choice box.
     */
    @FXML
    protected void setSize() {
        settings[0] = Integer.toString(cbSize.getValue());
    }

    /**
     * The method sets the color of player1 based on color picker.
     */
    @FXML
    protected void setColorX() {
        Color color = colorPicker1.getValue();
        rect1.setFill(colorPicker1.getValue());
        settings[2] = color.getRed() + " " + color.getGreen() + " " + color.getBlue();
        setToggleButtonColor();
    }

    /**
     * The method sets the color of player2 based on color picker.
     */
    @FXML
    protected void setColorY() {
        Color color = colorPicker2.getValue();
        rect2.setFill(colorPicker2.getValue());
        settings[3] = color.getRed() + " " + color.getGreen() + " " + color.getBlue();
        setToggleButtonColor();
    }

    /**
     * The method sets the first player who's playing based on the toggle button.
     */
    @FXML
    protected void setFirstPlayer() {
        if (startingPlayer1.isSelected()) { settings[1] = "X"; }
        if (startingPlayer2.isSelected()) { settings[1] = "O"; }
    }

    /**
     * The method saves the settings array to file, and returns to main menu.
     */
    @FXML
    protected void saveSettings() {
        try {
            PrintWriter writer = new PrintWriter((fileName), "utf-8");
            for (String line : settings) { writer.println(line); }
            writer.close();
        } catch (FileNotFoundException ex) { System.out.println("Unable to open settings");
        } catch (IOException ex) { System.out.println("Error reading settings"); }
        loadMainMenu();
    }

    /**
     * The method loads the main menu, and display it.
     */
    @FXML
    protected void loadMainMenu() {
        try {
            Stage stage = (Stage) this.cancelButton.getScene().getWindow();
            AnchorPane root = FXMLLoader.load(getClass().getResource("files/menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("files/app.css").toExternalForm());
            stage.setTitle("Othello");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
