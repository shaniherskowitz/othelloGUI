package sample;



import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.*;

import javax.xml.soap.Text;
import java.awt.Color;


public class Controller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageText;
    @FXML
    protected void signIn() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (username.equals("roi") && password.equals("123456")) {
            messageText.setText("You are approved!");
            //messageText.setFill(Color.BLUE);


        }
        else {
            messageText.setText("Invalid username or password");
            //messageText.setFill(Color.RED);
        }
    }
}
