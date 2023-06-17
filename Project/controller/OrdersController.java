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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrdersController implements MainController, Initializable {

    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ratingsBtn, aboutUsBtn, accountBtn, logoutBtn;

    @FXML
    Label contactNameLbl, contactNumberLbl, deliveryAddressLbl, paymentMethodLbl, totalPriceLbl;

    @FXML
    Pane pane = new Pane();

    @FXML
    Group group1;

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
    CheckoutController checkoutController = new CheckoutController();

    @FXML
    OrderHistoryController orderHistoryController;

    @FXML
    AccountController accountController = null;

    @FXML
    LoginController loginController = null;

    @FXML
    private TableView<Product> tableView = new TableView<Product>();

    @FXML
    private TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");

    @FXML
    private TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");

    @FXML
    private TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");

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

        // Applies the style sheet to the next or "to-be-loaded" scene/page (A.K.A. the fxml files):
        String style = this.getClass().getResource(css).toExternalForm();
        scene.getStylesheets().add(style);

        stage.setScene(scene);
        stage.show();
    }

    // Method that handles the visibility of the pane:
    // Disables the visiblity if there are no placed orders:
    public void toggleVisibility() {
        OrderList orderList = OrderList.getInstance();
        boolean isListEmpty = orderList.isOrderListEmpty();
        if (isListEmpty) {
            pane.setVisible(false);
            group1.setVisible(true);
        }
    }

    // Method that computes the total price of items that the user ordered:
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));

        // Gets the data list of the instance where the items that the user ordered is stored:
        List<Product> dataList = OrderList.getInstance().getDataList();

        // Displays the data list to the table view:
        ObservableList<Product> observableList = FXCollections.observableArrayList(dataList);
        tableView.setItems(observableList);

        computeTotal();
        toggleVisibility();

        // Gets the data list of the instance where it contains the user's order details:
        // Used to display the user's information upon placing their order:
        List<Customer> dataList2 = OrderDetails.getInstance().getDataList();
        for (Customer customer : dataList2) {
            String customerName = customer.getCustomerName();
            String contactNumber = customer.getContactNumber();
            String deliveryAddress = customer.getDeliveryAddress();
            String paymentMethod = customer.getPaymentMethod();

            contactNameLbl.setText(customerName);
            contactNumberLbl.setText(contactNumber);
            deliveryAddressLbl.setText(deliveryAddress);
            paymentMethodLbl.setText(paymentMethod);
        }
    }
}