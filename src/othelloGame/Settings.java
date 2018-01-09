package othelloGame;

import javafx.scene.paint.Color;

import java.io.*;

/**
 * Created by Liora on 09-Jan-18.
 */
public class Settings {
    private int size = 8;
    private String firstPlayer = "X";
    private Color colorX = Color.BLACK;
    private Color colorY = Color.WHITE;
    private static String fileName = "othelloGame/gameSettings";

    private Settings(int size, String firstPlayer, Color colorX, Color colorY) {
        this.size = size;
        this.firstPlayer = firstPlayer;
        this.colorX = colorX;
        this.colorY = colorY;
    }

    public int getSize() { return this.size; }

    public String getFirstPlayer() { return firstPlayer; }

    public Color getColorX() { return colorX; }

    public Color getColorY() { return colorY; }

    public static Settings loadSettings() {
        try {
           // String fileName = "othelloGame/gameSettings";
            String line, firstPlayer;
            int size;
            Color colorX, colorY;
            String[] setting;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            line = bufferedReader.readLine();
            //setting = line.split(":");
            size = Integer.parseInt(line);

            line = bufferedReader.readLine();
            //setting = line.split(":");
            firstPlayer = line;

            line = bufferedReader.readLine();
            //setting = line.split(":");
            colorX = Settings.parseToColor(line);

            line = bufferedReader.readLine();
            //setting = line.split(":");
            colorY = Settings.parseToColor(line);

            bufferedReader.close();
            return new Settings(size, firstPlayer, colorX, colorY);

        } catch (FileNotFoundException ex) { System.out.println("Unable to open settings");
        } catch (IOException ex) { System.out.println("Error reading settings"); }
        return defaultSettings();
    }

    private static Color parseToColor(String color) {
        return Color.BLACK;
    }

    private String parseColorToString(Color color) {
        return "black";
    }

    public static Settings defaultSettings() {
        String firstPlayer = "X";
        int size = 8;
        Color colorX = Color.BLACK, colorY = Color.WHITE;
        return new Settings(size, firstPlayer, colorX, colorY);
    }

    public void setSize(int size) {
        if (size >= 4 && size <= 20) {
            this.size = size;
            setSetting(Integer.toString(size), 1);
        }
    }

    public void setColorX(String colorX) {
        this.colorX = parseToColor(colorX);
        setSetting(colorX, 3);
    }

    public void setColorY(String colorY) {
        this.colorY = parseToColor(colorY);
        setSetting(colorY, 4);
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
        setSetting(firstPlayer, 2);
    }

    private void setSetting(String setting, int line) {
        try {
            PrintWriter writer = new PrintWriter((fileName), "utf-8");
            int i = 0;
            while (i < line - 1) {
                writer.println();
                i++;
            }
            writer.println(setting);
            writer.close();

        } catch (FileNotFoundException ex) { System.out.println("Unable to open settings");
        } catch (IOException ex) { System.out.println("Error reading settings"); }
    }
}
