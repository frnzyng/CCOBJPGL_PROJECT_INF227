package controller;

import model.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import alert.AlertMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckoutController implements MainController, Initializable {

    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ratingsBtn, aboutUsBtn, placeOrderBtn, accountBtn, logoutBtn;

    @FXML
    ChoiceBox<String> paymentChoiceBox;

    @FXML
    Group group1, group2;

    @FXML
    TextField nameField, numberField, addressField;

    @FXML
    Label totalPriceLbl, warningLbl;

    @FXML
    HomeController homeController = null;

    @FXML
    MenuController menuController = null;

    @FXML
    CartController cartController = new CartController();

    @FXML
    RatingsController ratingsController = null;

    @FXML
    AboutUsController aboutUsController = null;

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

    // Gets the current date and time
    LocalDateTime dateTimeNow = LocalDateTime.now();

    // Formats the date and time to the appropriate/desired format
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mm:ss a");
    String orderDate = dateTimeNow.format(formatDate);
    String orderTime = dateTimeNow.format(formatTime);

    // Array for initializing the choice box:
    String [] paymentMethod = {"Cash on Delivery", "GCash", "Maya"};
    
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

    // Method that places the order of the user and other necessary procedures:
    public void placeOrder() {
        // Retrieves the user's input:
        String name = nameField.getText().trim();
        String number = numberField.getText().trim();
        String address = addressField.getText().trim();
        String paymentMethod = paymentChoiceBox.getValue();

        if (name.isEmpty() || number.isEmpty() || address.isEmpty() || paymentMethod == null) {
            warningLbl.setVisible(true);
        }
        else {
            // Sets the customer name, number, and address to the customer instance; to be used in the OrdersController:
            Customer customer = new Customer(name, number, address, paymentMethod);
            customer.setCustomerName(name);
            customer.setContactNumber(number);
            customer.setDeliveryAddress(address);
            customer.setPaymentMethod(paymentMethod);

            // Clears the order list in the orders page:
            OrderList orderList = OrderList.getInstance();
            orderList.clearData();

            // Updates the product stock after placing an order:
            // Initializes the items from the checkout to the order list:
            for (Product product : tableView.getItems()) {
                List<Product> cartList = CartList.getInstance().getDataList();
                String productName = product.getProductName();
                int productQuantity = product.getProductQuantity();

                // For Menu Category 1:
                if (productName.equals(MenuCategory1Controller.product1.getProductName())) {
                    if (ProductCategory1.productStock1 > 0 && productQuantity <= ProductCategory1.productStock1) {
                        ProductCategory1.productStock1 = ProductCategory1.productStock1 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");   
                    }
                }
                if (productName.equals(MenuCategory1Controller.product2.getProductName())) {
                    if (ProductCategory1.productStock2 > 0 && productQuantity <= ProductCategory1.productStock2) {
                        ProductCategory1.productStock2 = ProductCategory1.productStock2 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product3.getProductName())) {
                    if (ProductCategory1.productStock3 > 0 && productQuantity <= ProductCategory1.productStock3) {
                        ProductCategory1.productStock3 = ProductCategory1.productStock3 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product4.getProductName())) {
                    if (ProductCategory1.productStock4 > 0 && productQuantity <= ProductCategory1.productStock4) {
                        ProductCategory1.productStock4 = ProductCategory1.productStock4 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product5.getProductName())) {
                    if (ProductCategory1.productStock5 > 0 && productQuantity <= ProductCategory1.productStock5) {
                        ProductCategory1.productStock5 = ProductCategory1.productStock5 - productQuantity; 
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product6.getProductName())) {
                    if (ProductCategory1.productStock6 > 0 && productQuantity <= ProductCategory1.productStock6) {
                        ProductCategory1.productStock6 = ProductCategory1.productStock6 - productQuantity;  
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product7.getProductName())) {
                    if (ProductCategory1.productStock7 > 0 && productQuantity <= ProductCategory1.productStock7) {
                        ProductCategory1.productStock7 = ProductCategory1.productStock7 - productQuantity;  
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product8.getProductName())) {
                    if (ProductCategory1.productStock8 > 0 && productQuantity <= ProductCategory1.productStock8) {
                        ProductCategory1.productStock8 = ProductCategory1.productStock8 - productQuantity; 
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product9.getProductName())) {
                    if (ProductCategory1.productStock9 > 0 && productQuantity <= ProductCategory1.productStock9) {
                        ProductCategory1.productStock9 = ProductCategory1.productStock9 - productQuantity; 
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product10.getProductName())) {
                    if (ProductCategory1.productStock10 > 0 && productQuantity <= ProductCategory1.productStock10) {
                        ProductCategory1.productStock10 = ProductCategory1.productStock10 - productQuantity;   
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product11.getProductName())) {
                    if (ProductCategory1.productStock11 > 0 && productQuantity <= ProductCategory1.productStock11) {
                        ProductCategory1.productStock11 = ProductCategory1.productStock11 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory1Controller.product12.getProductName())) {
                    if (ProductCategory1.productStock12 > 0 && productQuantity <= ProductCategory1.productStock12) {
                        ProductCategory1.productStock12 = ProductCategory1.productStock12 - productQuantity;    
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }

                // For Menu Category 2:
                if (productName.equals(MenuCategory2Controller.product1.getProductName())) {
                    if (ProductCategory2.productStock1 > 0 && productQuantity <= ProductCategory2.productStock1) {
                        ProductCategory2.productStock1 = ProductCategory2.productStock1 - productQuantity; 
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product2.getProductName())) {
                    if (ProductCategory2.productStock2 > 0 && productQuantity <= ProductCategory2.productStock2) {
                        ProductCategory2.productStock2 = ProductCategory2.productStock2 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product3.getProductName())) {
                    if (ProductCategory2.productStock3 > 0 && productQuantity <= ProductCategory2.productStock3) {
                        ProductCategory2.productStock3 = ProductCategory2.productStock3 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product4.getProductName())) {
                    if (ProductCategory2.productStock4 > 0 && productQuantity <= ProductCategory2.productStock4) {
                        ProductCategory2.productStock4 = ProductCategory2.productStock4 - productQuantity;    
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product5.getProductName())) {
                    if (ProductCategory2.productStock5 > 0 && productQuantity <= ProductCategory2.productStock5) {
                        ProductCategory2.productStock5 = ProductCategory2.productStock5 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product6.getProductName())) {
                    if (ProductCategory2.productStock6 > 0 && productQuantity <= ProductCategory2.productStock6) {
                        ProductCategory2.productStock6 = ProductCategory2.productStock6 - productQuantity;    
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product7.getProductName())) {
                    if (ProductCategory2.productStock7 > 0 && productQuantity <= ProductCategory2.productStock7) {
                        ProductCategory2.productStock7 = ProductCategory2.productStock7 - productQuantity;    
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory2Controller.product8.getProductName())) {
                    if (ProductCategory2.productStock8 > 0 && productQuantity <= ProductCategory2.productStock8) {
                        ProductCategory2.productStock8 = ProductCategory2.productStock8 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }

                // For Menu Category 3:
                if (productName.equals(MenuCategory3Controller.product1.getProductName())) {
                    if (ProductCategory3.productStock1 > 0 && productQuantity <= ProductCategory3.productStock1) {
                        ProductCategory3.productStock1 = ProductCategory3.productStock1 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product2.getProductName())) {
                    if (ProductCategory3.productStock2 > 0 && productQuantity <= ProductCategory3.productStock2) {
                        ProductCategory3.productStock2 = ProductCategory3.productStock2 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product3.getProductName())) {
                    if (ProductCategory3.productStock3 > 0 && productQuantity <= ProductCategory3.productStock3) {
                        ProductCategory3.productStock3 = ProductCategory3.productStock3 - productQuantity; 
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product4.getProductName())) {
                    if (ProductCategory3.productStock4 > 0 && productQuantity <= ProductCategory3.productStock4) {
                        ProductCategory3.productStock4 = ProductCategory3.productStock4 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product5.getProductName())) {
                    if (ProductCategory3.productStock5 > 0 && productQuantity <= ProductCategory3.productStock5) {
                        ProductCategory3.productStock5 = ProductCategory3.productStock5 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product6.getProductName())) {
                    if (ProductCategory3.productStock6 > 0 && productQuantity <= ProductCategory3.productStock6) {
                        ProductCategory3.productStock6 = ProductCategory3.productStock6 - productQuantity;    
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product7.getProductName())) {
                    if (ProductCategory3.productStock7 > 0 && productQuantity <= ProductCategory3.productStock7) {
                        ProductCategory3.productStock7 = ProductCategory3.productStock7 - productQuantity;            
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
                if (productName.equals(MenuCategory3Controller.product8.getProductName())) {
                    if (ProductCategory3.productStock8 > 0 && productQuantity <= ProductCategory3.productStock8) {
                        ProductCategory3.productStock8 = ProductCategory3.productStock8 - productQuantity;
                    }
                    else {
                        cartList.remove(product);
                        computeTotal();
                        initializeTable();
                        AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item (" + productName + ") is out of stock to complete your order, it will not be included in your orders.");
                    }
                }
            }

            //  Add the customer details to the list:
            OrderDetails.getInstance().addData(customer);

            // Clears the cart:
            CartList cartList = CartList.getInstance();
            cartList.clearData();

            // Adds the items displayed in the table:
            for (Product product : tableView.getItems()) {
                orderList.addData(product);
            }

            // Records the order in the order history:
            OrderHistory orderHistory = OrderHistory.getInstance();
            for (Product product : OrderList.getInstance().getDataList()) {
                String productName = product.getProductName();
                Double productPrice = product.getProductPrice();
                int productQuantity = product.getProductQuantity();

                Order data = new Order(productName, productPrice, productQuantity, orderDate, orderTime);
                orderHistory.addData(data);
            }

            // Change visibility if order is successful:
            group1.setVisible(false);
            group2.setVisible(true);
        }  
    }

    // Method that initializes the table view:
    public void initializeTable() {
        // Gets the data list of the instance where the items that are added to the cart is stored:
        List<Product> dataList = CartList.getInstance().getDataList();
        // Displays the data list to the table view:
        ObservableList<Product> observableList = FXCollections.observableArrayList(dataList);
        tableView.setItems(observableList);
    }

    // Method that computes the total price of items from the checkout page:
    public void computeTotal() {
        double totalPrice = 0.0;

        // Gets the items on the cart that is displayed in the table view and stores it in a list:
        List<Product> cartList = CartList.getInstance().getDataList();
        
        for (Product data : cartList) {
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
        paymentChoiceBox.getItems().addAll(paymentMethod);

        // Initializes the table:
        initializeTable();
        
        // Computes the total price of items in the cart during initialization or every time the scene loads:
        computeTotal();

        // Event listener for setting the warning label to false if the username or password text field is focus within:
        nameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                warningLbl.setVisible(false); 
            } 
        });
        numberField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                warningLbl.setVisible(false); 
            } 
        });
        addressField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                warningLbl.setVisible(false); 
            } 
        });
        paymentChoiceBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                warningLbl.setVisible(false);
            } 
        });
    }
}