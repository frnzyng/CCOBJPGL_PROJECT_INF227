package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;

public class LoginController implements MainController, Initializable{

    @FXML
    ImageView backgroundImg, logoImg;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginBtn;

    @FXML
    Label warningLbl;

    @FXML
    LoginController loginController = null;

    @FXML
    HomeController homeController = null;

    public String username, password;

    // Method that validates the username and password before logging in:
    public void login (ActionEvent event) throws IOException {
        username = usernameField.getText();
        password = passwordField.getText();
        Customer.getInstance().setUsername(username); 

        if (username.equals("a") && password.equals("a")) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
            Parent root = loader.load();
            homeController = loader.getController();

            Scene scene = new Scene(root);

            // Applies the style sheet to the next or "to-be-loaded" scene/page (A.K.A. the fxml files):
            String css = this.getClass().getResource("/css/Home.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage. setResizable(false);
            stage.setScene(scene);
            stage.show();
        } 
        else {
            warningLbl.setVisible(true); 
        }
    }

    @Override
    public void navigationBar(ActionEvent event) throws IOException {
     
    }

    @Override
    public void navigateToPage(ActionEvent event, MainController controller, String url, String css) throws IOException {
  
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Event listener for setting the warning label to false if the username or password text field is focus within:
        usernameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                warningLbl.setVisible(false); 
            } 
        });
        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                warningLbl.setVisible(false);
            } 
        });
    }
}