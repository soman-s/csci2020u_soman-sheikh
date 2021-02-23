package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private DatePicker DOB;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Username: " + username.getText());
        System.out.println("Password: " + password.getText());
        System.out.println("Name: " + name.getText());
        System.out.println("Username: " + email.getText());
        System.out.println("Phone: " + phone.getText());
        System.out.println("Date of birth: " + DOB.getValue());
    }
}
