package controller;

import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HomeController implements MainController, Initializable {

    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ratingsBtn, aboutUsBtn, accountBtn, logoutBtn;

    @FXML
    Label welcomeLbl;

    @FXML
    HomeController homeController = null;

    @FXML
    MenuController menuController = null;

    @FXML
    CartController cartController = null;

    @FXML
    RatingsController ratingsController = null;

    @FXML
    AboutUsController aboutUsController = null;

    @FXML
    AccountController accountController = null;

    @FXML
    LoginController loginController = new LoginController();

    // Method that handles the navigaton bar on the left side:
    public void navigationBar(ActionEvent event) throws IOException {
        Button sourceButton = (Button) event.getSource();
        String url;
        String css;

        if (sourceButton.equals(homeBtn)) {
            url = "/view/Home.fxml";
            css = "/css/Home.css";
            navigateToPage(event, homeController, url, css);
        } 
        else if (sourceButton.equals(menuBtn)) {
            url = "/view/Menu.fxml";
            css = "/css/Menu.css";
            navigateToPage(event, menuController, url, css);
        } 
        else if (sourceButton.equals(cartBtn)) {
            url = "/view/Cart.fxml";
            css = "/css/Cart.css";
            navigateToPage(event, cartController, url, css);
        } 
        else if (sourceButton.equals(ratingsBtn)) {
            url = "/view/Ratings.fxml";
            css = "/css/Ratings.css";
            navigateToPage(event, ratingsController, url, css);
        } 
        else if (sourceButton.equals(aboutUsBtn)) {
            url = "/view/AboutUs.fxml";
            css = "/css/AboutUs.css";
            navigateToPage(event, aboutUsController, url, css);
        } 
        else if (sourceButton.equals(accountBtn)) {
            url = "/view/Account.fxml";
            css = "/css/Account.css";
            navigateToPage(event, accountController, url, css);
        } 
        else if (sourceButton.equals(logoutBtn)) {
            url = "/view/Login.fxml";
            css = "/css/Login.css";
            navigateToPage(event, loginController, url, css);
        } 
        else {
            return;
        }
    }

    // Method that brings the user to a different page based on the navigation item (home, menu, cart, etc.) they clicked:
    public void navigateToPage(ActionEvent event, MainController controller, String url, String css) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        controller = loader.getController();
    
        Scene scene = new Scene(root);

        // Applies the style sheet to the next or "to-be-loaded" scene/page (A.K.A. the fxml files):
        String style = this.getClass().getResource(css).toExternalForm();
        scene.getStylesheets().add(style);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String username = Customer.getInstance().getUsername();
        welcomeLbl.setText("Hello, " + username + "! Welcome to Chill & Swirl!");
    }
}