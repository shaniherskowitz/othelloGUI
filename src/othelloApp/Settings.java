package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Settings implements Initializable{
    @FXML
    Button saveButton;
    @FXML
    Button cancelButton;
    @FXML
    ColorPicker colorPickerX;
    @FXML
    ColorPicker colorPickerY;
    @FXML
    ChoiceBox<Integer> cbSize;
    @FXML
    ToggleButton startingPlayer;
    @FXML
    Rectangle circleX;
    @FXML
    Rectangle circleY;

    private String[] settings = null;
    private static String fileName = "othelloApp/gameSettings";

    private Settings(String[] settings) {
        this.settings = settings;
    }

    public Settings() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateSelf();
        cbSize.setValue(getSize());
        Color colorX = parseToColor(settings[2]);
        Color colorY = parseToColor(settings[3]);
        colorPickerX.setValue(colorX);
        colorPickerY.setValue(colorY);
        circleX.setFill(colorX);
        circleY.setFill(colorY);
    }

    public void updateSelf() {
        this.settings = loadSettings().settings;
    }

    public int getSize() { return Integer.parseInt(settings[0]); }

    public String getFirstPlayer() { return settings[1]; }

    public Color getColorX() { return parseToColor(settings[2]); }

    public Color getColorY() { return parseToColor(settings[3]); }

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

    private static Color parseToColor(String color) {
        String [] rgb = color.split(" ");
        double r =Double.parseDouble(rgb[0]);
        double g = Double.parseDouble(rgb[1]);
        double b = Double.parseDouble(rgb[2]);
        return Color.color(r, g, b);
    }

    public static Settings defaultSettings() {
        String[] settings = new String[4];
        settings[0] = "8";
        settings[1] = "X";
        settings[2] = "0.1 0.0 0.0";
        settings[3] = "0.0 0.1 0.0";
        return new Settings(settings);
    }
    @FXML
    public void setSize() {
        settings[0] = Integer.toString(cbSize.getValue());
    }
    @FXML
    public void setColorX() {
        Color color = colorPickerX.getValue();
        circleX.setFill(colorPickerX.getValue());
        settings[2] = color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }
    @FXML
    public void setColorY() {
        Color color = colorPickerY.getValue();
        circleY.setFill(colorPickerY.getValue());
        settings[3] = color.getRed() + " " + color.getGreen() + " " + color.getBlue();
    }
    @FXML
    public void setFirstPlayer() {
        if (startingPlayer.isSelected()) { settings[1] = "X"; }
        else { settings[1] = "O"; }
    }
    @FXML
    public void saveSettings() {
        try {
            PrintWriter writer = new PrintWriter((fileName), "utf-8");
            for (String line : settings) { writer.println(line); }
            writer.close();
        } catch (FileNotFoundException ex) { System.out.println("Unable to open settings");
        } catch (IOException ex) { System.out.println("Error reading settings"); }
        endScene();
    }

    public void runSettingScene(Stage stage) {
        try {
            GridPane settingsPage = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene scene = new Scene(settingsPage, 520, 400);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { System.out.print("Exception in loading settings fxml"); }

    }
    @FXML
    public void endScene() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

}
