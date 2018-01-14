package othelloApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Settings implements Initializable {
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
    ToggleButton startingPlayer1;
    @FXML
    ToggleButton startingPlayer2;
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
        Color color1 = parseToColor(settings[2]);
        Color color2 = parseToColor(settings[3]);
        colorPickerX.setValue(color1);
        colorPickerY.setValue(color2);
        circleX.setFill(color1);
        circleY.setFill(color2);
        setToggleButtonColor();
    }

    public void setToggleButtonColor() {
        Color color1 = colorPickerX.getValue();
        Color color2 = colorPickerY.getValue();
        String stringColor1 = "-fx-color: rgb(" + color1.getRed()*255 + ", "
                + color1.getGreen()*255 + ", " + color1.getBlue()*255 + ")";
        String stringColor2 = "-fx-color: rgb(" + color2.getRed()*255 + ", "
                + color2.getGreen()*255 + ", " + color2.getBlue()*255 + ")";
        startingPlayer1.setStyle(stringColor1);
        startingPlayer2.setStyle(stringColor2);
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
        setToggleButtonColor();
    }
    @FXML
    public void setColorY() {
        Color color = colorPickerY.getValue();
        circleY.setFill(colorPickerY.getValue());
        settings[3] = color.getRed() + " " + color.getGreen() + " " + color.getBlue();
        setToggleButtonColor();
    }
    @FXML
    public void setFirstPlayer() {
        if (startingPlayer1.isSelected()) { settings[1] = "X"; }
        if (startingPlayer2.isSelected()) { settings[1] = "O"; }
    }
    @FXML
    public void saveSettings() {
        try {
            PrintWriter writer = new PrintWriter((fileName), "utf-8");
            for (String line : settings) { writer.println(line); }
            writer.close();
        } catch (FileNotFoundException ex) { System.out.println("Unable to open settings");
        } catch (IOException ex) { System.out.println("Error reading settings"); }
        loadMainMenu();
    }

    @FXML
    public void loadMainMenu() {
        try {
            Stage stage = (Stage) this.cancelButton.getScene().getWindow();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(root, 520, 400);
            scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
            stage.setTitle("Othello");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
