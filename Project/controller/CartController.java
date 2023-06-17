package controller;

import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CartController implements MainController, Initializable {

    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ratingsBtn, aboutUsBtn, removeBtn, checkoutBtn, accountBtn, logoutBtn;

    @FXML
    Label productLbl1, productLbl2, productLbl3, productLbl4, 
    priceLbl1, priceLbl2, priceLbl3, priceLbl4,
    quantityLbl1, quantityLbl2, quantityLbl3, quantityLbl4,
    totalPriceLbl;

    @FXML
    Pane pane1, pane2, pane3;

    @FXML
    HomeController homeController = null;

    @FXML
    MenuController menuController = null;

    @FXML
    CartController cartController = null;

    @FXML
    CheckoutController checkoutController = null;

    @FXML
    RatingsController ratingsController = null;

    @FXML
    AboutUsController aboutUsController = null;

    @FXML
    AccountController accountController = null;

    @FXML
    private TableView<Product> tableView = new TableView<Product>();

    @FXML
    private TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");

    @FXML
    private TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");

    @FXML
    private TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");

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
        else if (sourceButton.equals(checkoutBtn)) {
            // Gets the items on the cart that is displayed in the table view and stores it in a list:
            ObservableList<Product> dataList = tableView.getItems();

            if (!dataList.isEmpty()) {
                url = "/view/Checkout.fxml";
                css = "/css/Checkout.css";
                navigateToPage(event, checkoutController, url, css);
            }
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

    // Method that computes the total price of items from the cart:
    public void computeTotal() {
        double totalPrice = 0.0;

        // Gets the items on the cart that is displayed in the table view and stores it in a list:
        ObservableList<Product> dataList = tableView.getItems();
        
        for (Product data : dataList) {
            double price = data.getProductPrice();
            int quantity = data.getProductQuantity();
            totalPrice += price * quantity;
        }

        totalPriceLbl.setText("₱" + Double.toString(totalPrice));
    }

    // Method that removes the selected item from the cart:
    public void removeItem(ActionEvent event) {
        // Gets the selected item of the user:
        Product selectedData = tableView.getSelectionModel().getSelectedItem();
        if (selectedData != null) {
            ObservableList<Product> dataList = tableView.getItems();
            dataList.remove(selectedData);
            CartList.getInstance().remove(selectedData);

            // Computes the total price of items again whenever an item is removed from the cart:
            computeTotal();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));

        tableView.setPlaceholder(new Label("Your cart is empty :("));

        // Gets the data list of the instance where the items that are added to the cart is stored:
        List<Product> dataList = CartList.getInstance().getDataList();

        // Displays the data list to the table view:
        ObservableList<Product> observableList = FXCollections.observableArrayList(dataList);
        tableView.setItems(observableList);

        // Computes the total price of items in the cart during initialization or every time the scene loads:
        computeTotal();
    }
}