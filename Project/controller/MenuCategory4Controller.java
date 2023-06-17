package controller;

import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import alert.AlertMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuCategory4Controller extends MenuController implements Initializable{
    
    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ratingsBtn, aboutUsBtn, accountBtn, logoutBtn, nextBtn1, nextBtn2, confirmBtn;

    @FXML
    Label
    baseLbl1, baseLbl2, baseLbl3, baseLbl4,
    iceCreamLbl1, iceCreamLbl2, iceCreamLbl3, iceCreamLbl4, iceCreamLbl5, iceCreamLbl6,
    syrupLbl1, syrupLbl2, syrupLbl3, syrupLbl4, syrupLbl5, syrupLbl6,
    toppingsLbl1, toppingsLbl2, toppingsLbl3, toppingsLbl4, toppingsLbl5, toppingsLbl6,
    basePriceLbl1, basePriceLbl2, basePriceLbl3, basePriceLbl4,
    iceCreamPriceLbl1, iceCreamPriceLbl2, iceCreamPriceLbl3, iceCreamPriceLbl4, iceCreamPriceLbl5, iceCreamPriceLbl6,
    syrupPriceLbl1, syrupPriceLbl2, syrupPriceLbl3, syrupPriceLbl4, syrupPriceLbl5, syrupPriceLbl6,
    toppingsPriceLbl1, toppingsPriceLbl2, toppingsPriceLbl3, toppingsPriceLbl4, toppingsPriceLbl5, toppingsPriceLbl6,
    totalPriceLbl;

    @FXML
    Group group1, group2, group3, group4, group5, group6;

    @FXML
    RadioButton radioBtn1, radioBtn2, radioBtn3, radioBtn4;

    @FXML
    CheckBox iceCreamCheckBox1, iceCreamCheckBox2, iceCreamCheckBox3, iceCreamCheckBox4, iceCreamCheckBox5, iceCreamCheckBox6,
    syrupCheckBox1, syrupCheckBox2, syrupCheckBox3, syrupCheckBox4, syrupCheckBox5, syrupCheckBox6,
    toppingsCheckBox1, toppingsCheckBox2, toppingsCheckBox3, toppingsCheckBox4, toppingsCheckBox5, toppingsCheckBox6;

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

    @FXML
    private TableView<Product> tableView = new TableView<Product>();

    @FXML
    private TableColumn<Product, String> productNameColumn = new TableColumn<>("Product Name");

    @FXML
    private TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");

    @FXML
    private TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");

    // Temporary list for storing each components (base, ice cream flavor, syrup, toppings) of the customize ice cream selected by the user:
    private List<Product> temporaryList = new ArrayList<>();

    // Sets the limit of how many checkbox can be checked:
    private static final int maxSelected = 3;
    private int iceCreamSelectedCount = 0;
    private int syrupSelectedCount = 0;
    private int toppingsSelectedCount = 0;

    ProductCategory4 base1 = new ProductCategory4(null, 0, 0);
    ProductCategory4 base2 = new ProductCategory4(null, 0, 0);
    ProductCategory4 base3 = new ProductCategory4(null, 0, 0);
    ProductCategory4 base4 = new ProductCategory4(null, 0, 0);

    ProductCategory4 iceCream1 = new ProductCategory4(null, 0, 0);
    ProductCategory4 iceCream2 = new ProductCategory4(null, 0, 0);
    ProductCategory4 iceCream3 = new ProductCategory4(null, 0, 0);
    ProductCategory4 iceCream4 = new ProductCategory4(null, 0, 0);
    ProductCategory4 iceCream5 = new ProductCategory4(null, 0, 0);
    ProductCategory4 iceCream6 = new ProductCategory4(null, 0, 0);

    ProductCategory4 syrup1 = new ProductCategory4(null, 0, 0);
    ProductCategory4 syrup2 = new ProductCategory4(null, 0, 0);
    ProductCategory4 syrup3 = new ProductCategory4(null, 0, 0);
    ProductCategory4 syrup4 = new ProductCategory4(null, 0, 0);
    ProductCategory4 syrup5 = new ProductCategory4(null, 0, 0);
    ProductCategory4 syrup6 = new ProductCategory4(null, 0, 0);

    ProductCategory4 toppings1 = new ProductCategory4(null, 0, 0);
    ProductCategory4 toppings2 = new ProductCategory4(null, 0, 0);
    ProductCategory4 toppings3 = new ProductCategory4(null, 0, 0);
    ProductCategory4 toppings4 = new ProductCategory4(null, 0, 0);
    ProductCategory4 toppings5 = new ProductCategory4(null, 0, 0);
    ProductCategory4 toppings6 = new ProductCategory4(null, 0, 0);

    public void initializeProducts() {
        // Base:
        base1.setProductName("Cone");
        base1.setProductPrice(20.00);
        baseLbl1.setText(base1.getProductName());
        basePriceLbl1.setText("₱" + Double.toString(base1.getProductPrice()));

        base2.setProductName("Cone Cup");
        base2.setProductPrice(20.00);
        baseLbl2.setText(base2.getProductName());
        basePriceLbl2.setText("₱" + Double.toString(base2.getProductPrice()));

        base3.setProductName("Waffle");
        base3.setProductPrice(20.00);
        baseLbl3.setText(base3.getProductName());
        basePriceLbl3.setText("₱" + Double.toString(base3.getProductPrice()));

        base4.setProductName("Paper Cup");
        base4.setProductPrice(20.00);
        baseLbl4.setText(base4.getProductName());
        basePriceLbl4.setText("₱" + Double.toString(base4.getProductPrice()));
        
        // Ice Cream Flavors:
        iceCream1.setProductName("Chocolate");
        iceCream1.setProductPrice(30.00);
        iceCreamLbl1.setText(iceCream1.getProductName());
        iceCreamPriceLbl1.setText("₱" + Double.toString(iceCream1.getProductPrice()));

        iceCream2.setProductName("Vanilla");
        iceCream2.setProductPrice(30.00);
        iceCreamLbl2.setText(iceCream2.getProductName());
        iceCreamPriceLbl2.setText("₱" + Double.toString(iceCream2.getProductPrice()));

        iceCream3.setProductName("Strawberry");
        iceCream3.setProductPrice(30.00);
        iceCreamLbl3.setText(iceCream3.getProductName());
        iceCreamPriceLbl3.setText("₱" + Double.toString(iceCream3.getProductPrice()));

        iceCream4.setProductName("Matcha");
        iceCream4.setProductPrice(30.00);
        iceCreamLbl4.setText(iceCream4.getProductName());
        iceCreamPriceLbl4.setText("₱" + Double.toString(iceCream4.getProductPrice()));

        iceCream5.setProductName("Mocha");
        iceCream5.setProductPrice(30.00);
        iceCreamLbl5.setText(iceCream5.getProductName());
        iceCreamPriceLbl5.setText("₱" + Double.toString(iceCream5.getProductPrice()));

        iceCream6.setProductName("Pistachio");
        iceCream6.setProductPrice(30.00);
        iceCreamLbl6.setText(iceCream6.getProductName());
        iceCreamPriceLbl6.setText("₱" + Double.toString(iceCream6.getProductPrice()));

        // Syrup Flavors:
        syrup1.setProductName("Chocolate Syrup");
        syrup1.setProductPrice(10.00);
        syrupLbl1.setText(syrup1.getProductName());
        syrupPriceLbl1.setText("₱" + Double.toString(syrup1.getProductPrice()));

        syrup2.setProductName("White Chocolate Syrup");
        syrup2.setProductPrice(10.00);
        syrupLbl2.setText(syrup2.getProductName());
        syrupPriceLbl2.setText("₱" + Double.toString(syrup2.getProductPrice()));

        syrup3.setProductName("Strawberry Syrup");
        syrup3.setProductPrice(10.00);
        syrupLbl3.setText(syrup3.getProductName());
        syrupPriceLbl3.setText("₱" + Double.toString(syrup3.getProductPrice()));

        syrup4.setProductName("Caramel Syrup");
        syrup4.setProductPrice(10.00);
        syrupLbl4.setText(syrup4.getProductName());
        syrupPriceLbl4.setText("₱" + Double.toString(syrup4.getProductPrice()));

        syrup5.setProductName("Dark Chocolate Syrup");
        syrup5.setProductPrice(10.00);
        syrupLbl5.setText(syrup5.getProductName());
        syrupPriceLbl5.setText("₱" + Double.toString(syrup5.getProductPrice()));

        syrup6.setProductName("Maple Syrup");
        syrup6.setProductPrice(10.00);
        syrupLbl6.setText(syrup6.getProductName());
        syrupPriceLbl6.setText("₱" + Double.toString(syrup6.getProductPrice()));

        // Toppings:
        toppings1.setProductName("Chocolate Chips");
        toppings1.setProductPrice(10.00);
        toppingsLbl1.setText(toppings1.getProductName());
        toppingsPriceLbl1.setText("₱" + Double.toString(toppings1.getProductPrice()));

        toppings2.setProductName("Sprinkles");
        toppings2.setProductPrice(10.00);
        toppingsLbl2.setText(toppings2.getProductName());
        toppingsPriceLbl2.setText("₱" + Double.toString(toppings2.getProductPrice()));

        toppings3.setProductName("Brownies");
        toppings3.setProductPrice(10.00);
        toppingsLbl3.setText(toppings3.getProductName());
        toppingsPriceLbl3.setText("₱" + Double.toString(toppings3.getProductPrice()));

        toppings4.setProductName("Marshmallow");
        toppings4.setProductPrice(10.00);
        toppingsLbl4.setText(toppings4.getProductName());
        toppingsPriceLbl4.setText("₱" + Double.toString(toppings4.getProductPrice()));

        toppings5.setProductName("Graham Crumbs");
        toppings5.setProductPrice(10.00);
        toppingsLbl5.setText(toppings5.getProductName());
        toppingsPriceLbl5.setText("₱" + Double.toString(toppings5.getProductPrice()));

        toppings6.setProductName("Rice Crisps");
        toppings6.setProductPrice(10.00);
        toppingsLbl6.setText(toppings6.getProductName());
        toppingsPriceLbl6.setText("₱" + Double.toString(toppings6.getProductPrice()));
    }

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

    // Method that stores the user's chosen base for their custom ice cream:
    public void addBase(ActionEvent event) throws IOException {
        if (radioBtn1.isSelected()) {
            String productName = "Custom: Base: " + base1.getProductName();
            double productPrice = base1.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        else if (radioBtn2.isSelected()) {
            String productName = "Custom: Base: " + base2.getProductName();
            double productPrice = base2.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        else if (radioBtn3.isSelected()) {
            String productName = "Custom: Base: " + base3.getProductName();
            double productPrice = base3.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        else if (radioBtn4.isSelected()) {
            String productName = "Custom: Base: " + base4.getProductName();
            double productPrice = base4.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }

        if (group1.isVisible()) {
            group1.setVisible(false);
            group2.setVisible(true);
        }
    }

    // Method that stores the user's chosen ice cream flavor for their custom ice cream:
    public void addIceCream(ActionEvent event) throws IOException {
        if (iceCreamCheckBox1.isSelected()) {
            String productName = "Custom: Ice Cream: " + iceCream1.getProductName();
            double productPrice = iceCream1.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (iceCreamCheckBox2.isSelected()) {
            String productName = "Custom: Ice Cream: " + iceCream2.getProductName();
            double productPrice = iceCream2.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (iceCreamCheckBox3.isSelected()) {
            String productName = "Custom: Ice Cream: " + iceCream3.getProductName();
            double productPrice = iceCream3.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (iceCreamCheckBox4.isSelected()) {
            String productName = "Custom: Ice Cream: " + iceCream4.getProductName();
            double productPrice = iceCream4.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (iceCreamCheckBox5.isSelected()) {
            String productName = "Custom: Ice Cream: " + iceCream5.getProductName();
            double productPrice = iceCream5.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (iceCreamCheckBox6.isSelected()) {
            String productName = "Custom: Ice Cream: " + iceCream6.getProductName();
            double productPrice = iceCream6.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }

        if (group2.isVisible()) {
            group2.setVisible(false);
            group3.setVisible(true);
        }
    }

    // Method that stores the user's chosen syrup for their custom ice cream:
    public void addSyrup(ActionEvent event) throws IOException {
        if (syrupCheckBox1.isSelected()) {
            String productName = "Custom: Syrup: " + syrup1.getProductName();
            double productPrice = syrup1.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (syrupCheckBox2.isSelected()) {
            String productName = "Custom: Syrup: " + syrup2.getProductName();
            double productPrice = syrup2.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (syrupCheckBox3.isSelected()) {
            String productName = "Custom: Syrup: " + syrup3.getProductName();
            double productPrice = syrup3.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (syrupCheckBox4.isSelected()) {
            String productName = "Custom: Syrup: " + syrup4.getProductName();
            double productPrice = syrup4.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (syrupCheckBox5.isSelected()) {
            String productName = "Custom: Syrup: " + syrup5.getProductName();
            double productPrice = syrup5.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (syrupCheckBox6.isSelected()) {
            String productName = "Custom: Syrup: " + syrup6.getProductName();
            double productPrice = syrup6.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }

        if (group3.isVisible()) {
            group3.setVisible(false);
            group4.setVisible(true);
        }
    }

    // Method that stores the user's chosen syrup for their custom ice cream:
    public void addToppings(ActionEvent event) throws IOException {
        if (toppingsCheckBox1.isSelected()) {
            String productName = "Custom: Toppings: " + toppings1.getProductName();
            double productPrice = toppings1.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (toppingsCheckBox2.isSelected()) {
            String productName = "Custom: Toppings: " + toppings2.getProductName();
            double productPrice = toppings2.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (toppingsCheckBox3.isSelected()) {
            String productName = "Custom: Toppings: " + toppings3.getProductName();
            double productPrice = toppings3.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (toppingsCheckBox4.isSelected()) {
            String productName = "Custom: Toppings: " + toppings4.getProductName();
            double productPrice = toppings4.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (toppingsCheckBox5.isSelected()) {
            String productName = "Custom: Toppings: " + toppings5.getProductName();
            double productPrice = toppings5.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }
        if (toppingsCheckBox6.isSelected()) {
            String productName = "Custom: Toppings: " + toppings6.getProductName();
            double productPrice = toppings6.getProductPrice();
            int productQuantity = 1;

            ProductCategory4 data = new ProductCategory4(productName, productPrice, productQuantity);
            temporaryList.add(data);
        }

        if (group4.isVisible()) {
            group4.setVisible(false);
            group5.setVisible(true);
            initializeTable();
        }

        computeTotal();
    }

    // Initializes the table after customizing:
    public void initializeTable() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productQuantity"));

        // Displays the data list to the table view:
        ObservableList<Product> temporaryObservableList = FXCollections.observableArrayList(temporaryList);
        tableView.setItems(temporaryObservableList);
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

    // Method that handles the adding of items to the cart using a list:
    public void addToCart() {
        if (temporaryList.isEmpty()) {
            AlertMaker.showErrorAlert("Chill & Swirl","No Item Added to Cart", "Please choose to customize your ice cream.");
        }
        else {
            for (Product product : temporaryList) {
            CartList.getInstance().addData(product);
            }
            // Clear the temporary list after adding the custom ice cream to the cart:
            temporaryList.clear();
            group5.setVisible(false);
            group6.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group1.setVisible(true);

        initializeProducts();

        // Event handler for the ice cream checkboxes:
        javafx.event.EventHandler<javafx.event.ActionEvent> iceCreamCheckboxHandler = event -> {
            CheckBox iceCreamSelectedCheckbox = (CheckBox) event.getSource();
            if (iceCreamSelectedCheckbox.isSelected()) {
                if (iceCreamSelectedCount >= maxSelected) {
                    iceCreamSelectedCheckbox.setSelected(false);
                } else {
                    iceCreamSelectedCount++;
                }
            } else {
                iceCreamSelectedCount--;
            }
        };

        // Event handler for the syrup checkboxes:
        javafx.event.EventHandler<javafx.event.ActionEvent> syrupCheckboxHandler = event -> {
            CheckBox syrupSelectedCheckbox = (CheckBox) event.getSource();
            if (syrupSelectedCheckbox.isSelected()) {
                if (syrupSelectedCount >= maxSelected) {
                    syrupSelectedCheckbox.setSelected(false);
                } else {
                    syrupSelectedCount++;
                }
            } else {
                syrupSelectedCount--;
            }
        };

        // Event handler for the toppings checkboxes:
        javafx.event.EventHandler<javafx.event.ActionEvent> toppingsCheckboxHandler = event -> {
            CheckBox toppingsSelectedCheckbox = (CheckBox) event.getSource();
            if (toppingsSelectedCheckbox.isSelected()) {
                if (toppingsSelectedCount >= maxSelected) {
                    toppingsSelectedCheckbox.setSelected(false);
                } else {
                    toppingsSelectedCount++;
                }
            } else {
                toppingsSelectedCount--;
            }
        };

        // Add event handler to the checkboxes
        iceCreamCheckBox1.setOnAction(iceCreamCheckboxHandler);
        iceCreamCheckBox2.setOnAction(iceCreamCheckboxHandler);
        iceCreamCheckBox3.setOnAction(iceCreamCheckboxHandler);
        iceCreamCheckBox4.setOnAction(iceCreamCheckboxHandler);
        iceCreamCheckBox5.setOnAction(iceCreamCheckboxHandler);
        iceCreamCheckBox6.setOnAction(iceCreamCheckboxHandler);


        syrupCheckBox1.setOnAction(syrupCheckboxHandler);
        syrupCheckBox2.setOnAction(syrupCheckboxHandler);
        syrupCheckBox3.setOnAction(syrupCheckboxHandler);
        syrupCheckBox4.setOnAction(syrupCheckboxHandler);
        syrupCheckBox5.setOnAction(syrupCheckboxHandler);
        syrupCheckBox6.setOnAction(syrupCheckboxHandler);

        toppingsCheckBox1.setOnAction(toppingsCheckboxHandler);
        toppingsCheckBox2.setOnAction(toppingsCheckboxHandler);
        toppingsCheckBox3.setOnAction(toppingsCheckboxHandler);
        toppingsCheckBox4.setOnAction(toppingsCheckboxHandler);
        toppingsCheckBox5.setOnAction(toppingsCheckboxHandler);
        toppingsCheckBox6.setOnAction(toppingsCheckboxHandler);
    }
}