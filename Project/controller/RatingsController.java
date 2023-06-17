package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;

public class RatingsController implements MainController, Initializable {

    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ordersBtn, ratingsBtn, aboutUsBtn, accountBtn, logoutBtn, starBtn1, starBtn2, starBtn3;

    @FXML
    Group group1, group2;

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
    LoginController loginController = null;

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
        FXMLLoader loader;
        Parent root;
        loader = new FXMLLoader(getClass().getResource(url));
        root = loader.load();
        controller = loader.getController();
    
        Scene scene = new Scene(root);
        String style = this.getClass().getResource(css).toExternalForm();
        scene.getStylesheets().add(style);
        stage.setScene(scene);
        stage.show();
    }

    // Method that rates the shop:
    public void rate() {
        if(!Customer.getInstance().isRated()) {
            Customer.getInstance().setRatings(true);
            group1.setVisible(false);
            group2.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Customer.getInstance().getRatings()) {
            group1.setVisible(false);
            group2.setVisible(true);
        }

        // Event handlers for the star button when hovered:
        starBtn3.setOnMouseEntered(e -> {
            starBtn3.setStyle("-fx-background-color: null; -fx-text-fill: #E98BB7;");
            starBtn2.setStyle("-fx-background-color: null; -fx-text-fill: #E98BB7;");
            starBtn1.setStyle("-fx-background-color: null; -fx-text-fill: #E98BB7;");
        });
        starBtn3.setOnMouseExited(e -> {
            starBtn3.setStyle(null);
            starBtn2.setStyle(null);
            starBtn1.setStyle(null);
        });

        starBtn2.setOnMouseEntered(e -> {
            starBtn2.setStyle("-fx-background-color: null; -fx-text-fill: #E98BB7;");
            starBtn1.setStyle("-fx-background-color: null; -fx-text-fill: #E98BB7;");
        });
        starBtn2.setOnMouseExited(e -> {
            starBtn2.setStyle(null);
            starBtn1.setStyle(null);
        });

        starBtn1.setOnMouseEntered(e -> {
            starBtn1.setStyle("-fx-background-color: null; -fx-text-fill: #E98BB7;");
        });
        starBtn1.setOnMouseExited(e -> {
            starBtn1.setStyle(null);
        });
    }
}