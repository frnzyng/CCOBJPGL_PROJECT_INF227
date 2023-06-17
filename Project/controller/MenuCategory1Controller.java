package controller;

import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import alert.AlertMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class MenuCategory1Controller extends MenuController implements Initializable {
    
    FXMLLoader loader;

    @FXML
    Button homeBtn, menuBtn, cartBtn, ordersBtn, ratingsBtn, aboutUsBtn, accountBtn, logoutBtn,
    addBtn1, addBtn2, addBtn3, addBtn4, addBtn5, addBtn6,
    addBtn7, addBtn8, addBtn9, addBtn10, addBtn11, addBtn12,
    backBtn, nextBtn;

    @FXML
    Label productLbl1, productLbl2, productLbl3, productLbl4, productLbl5, productLbl6, 
    productLbl7, productLbl8, productLbl9, productLbl10, productLbl11, productLbl12,
    priceLbl1, priceLbl2, priceLbl3, priceLbl4, priceLbl5, priceLbl6,
    priceLbl7, priceLbl8, priceLbl9, priceLbl10, priceLbl11, priceLbl12;

    @FXML
    Group group1, group2;

    @FXML
    Spinner <Integer> quantitySpnr1, quantitySpnr2, quantitySpnr3, quantitySpnr4, quantitySpnr5, quantitySpnr6,
    quantitySpnr7, quantitySpnr8, quantitySpnr9, quantitySpnr10, quantitySpnr11, quantitySpnr12;

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
    private Parent root = null;

    static ProductCategory1 product1 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product2 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product3 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product4 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product5 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product6 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product7 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product8 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product9 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product10 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product11 = new ProductCategory1("null", 0, 0);
    static ProductCategory1 product12 = new ProductCategory1("null", 0, 0);

    // Method that initializes the name, price, and stock of the products upon displaying on the page:
    public void initializeProducts() {

        int productStock1 = ProductCategory1.productStock1;
        int productStock2 = ProductCategory1.productStock2;
        int productStock3 = ProductCategory1.productStock3;
        int productStock4 = ProductCategory1.productStock4;
        int productStock5 = ProductCategory1.productStock5;
        int productStock6 = ProductCategory1.productStock6;
        int productStock7 = ProductCategory1.productStock7;
        int productStock8 = ProductCategory1.productStock8;
        int productStock9 = ProductCategory1.productStock9;
        int productStock10 = ProductCategory1.productStock10;
        int productStock11 = ProductCategory1.productStock11;
        int productStock12 = ProductCategory1.productStock12;

        product1.setProductName("Chocolate");
        product1.setProductPrice(50.00);
        product1.setProductStock(productStock1);
        productLbl1.setText(product1.getProductName() + " (" + product1.getProductStock() +")");
        priceLbl1.setText("₱" + Double.toString(product1.getProductPrice()));

        product2.setProductName("Vanilla");
        product2.setProductPrice(50.00);
        product2.setProductStock(productStock2);
        productLbl2.setText(product2.getProductName() + " (" + product2.getProductStock() +")");
        priceLbl2.setText("₱" + Double.toString(product2.getProductPrice()));

        product3.setProductName("Strawberry");
        product3.setProductPrice(80.00);
        product3.setProductStock(productStock3);
        productLbl3.setText(product3.getProductName() + " (" + product3.getProductStock() +")");
        priceLbl3.setText("₱" + Double.toString(product3.getProductPrice()));

        product4.setProductName("Mocha");
        product4.setProductPrice(90.00);
        product4.setProductStock(productStock4);
        productLbl4.setText(product4.getProductName() + " (" + product4.getProductStock() +")");
        priceLbl4.setText("₱" + Double.toString(product4.getProductPrice()));

        product5.setProductName("Cookies & Cream");
        product5.setProductPrice(50.00);
        product5.setProductStock(productStock5);
        productLbl5.setText(product5.getProductName() + " (" + product5.getProductStock() +")");
        priceLbl5.setText("₱" + Double.toString(product5.getProductPrice()));

        product6.setProductName("Mint Choco");
        product6.setProductPrice(80.00);
        product6.setProductStock(productStock6);
        productLbl6.setText(product6.getProductName() + " (" + product6.getProductStock() +")");
        priceLbl6.setText("₱" + Double.toString(product6.getProductPrice()));

        product7.setProductName("Cheese Ice Cream");
        product7.setProductPrice(50.00);
        product7.setProductStock(productStock7);
        productLbl7.setText(product7.getProductName() + " (" + product7.getProductStock() +")");
        priceLbl7.setText("₱" + Double.toString(product7.getProductPrice()));

        product8.setProductName("Rocky Road");
        product8.setProductPrice(50.00);
        product8.setProductStock(productStock8);
        productLbl8.setText(product8.getProductName() + " (" + product8.getProductStock() +")");
        priceLbl8.setText("₱" + Double.toString(product8.getProductPrice()));

        product9.setProductName("KitKat Ice Cream");
        product9.setProductPrice(50.00);
        product9.setProductStock(productStock9);
        productLbl9.setText(product9.getProductName() + " (" + product9.getProductStock() +")");
        priceLbl9.setText("₱" + Double.toString(product9.getProductPrice()));

        product10.setProductName("Pistachio Ice Cream");
        product10.setProductPrice(90.00);
        product10.setProductStock(productStock10);
        productLbl10.setText(product10.getProductName() + " (" + product10.getProductStock() +")");
        priceLbl10.setText("₱" + Double.toString(product10.getProductPrice()));

        product11.setProductName("Matcha");
        product11.setProductPrice(90.00);
        product11.setProductStock(productStock11);
        productLbl11.setText(product11.getProductName() + " (" + product11.getProductStock() +")");
        priceLbl11.setText("₱" + Double.toString(product11.getProductPrice()));

        product12.setProductName("Coffee Crumble");
        product12.setProductPrice(80.00);
        product12.setProductStock(productStock12);
        productLbl12.setText(product12.getProductName() + " (" + product12.getProductStock() +")");
        priceLbl12.setText("₱" + Double.toString(product12.getProductPrice()));
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
    
    // Method that switches the page when the arrow buttons is clicked:
    public void switchPage(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        
        if (sourceButton.equals(backBtn)) {
            group2.setVisible(false);
            group1.setVisible(true);
        }
        else if (sourceButton.equals(nextBtn)) {
            group1.setVisible(false);
            group2.setVisible(true);
        }
    }
    
    // Method that handles the adding of items to the cart:
    public void addToCart(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
    
        if (sourceButton.equals(addBtn1)) {
            String productName = product1.getProductName();
            double productPrice = product1.getProductPrice();
            int productQuantity = quantitySpnr1.getValue();
            int productStock = product1.getProductStock();

            checkItemStock(productName, productPrice, productQuantity, productStock);     
        }
        if (sourceButton.equals(addBtn2)) {
            String productName = product2.getProductName();
            double productPrice = product2.getProductPrice();
            int productQuantity = quantitySpnr2.getValue();
            int productStock = product2.getProductStock();

            checkItemStock(productName, productPrice, productQuantity, productStock);   
        }
        if (sourceButton.equals(addBtn3)) {
            String productName = product3.getProductName();
            double productPrice = product3.getProductPrice();
            int productQuantity = quantitySpnr3.getValue();
            int productStock = product3.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);   
        }
        if (sourceButton.equals(addBtn4)) {
            String productName = product4.getProductName();
            double productPrice = product4.getProductPrice();
            int productQuantity = quantitySpnr4.getValue();
            int productStock = product4.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);   
        }
        if (sourceButton.equals(addBtn5)) {
            String productName = product5.getProductName();
            double productPrice = product5.getProductPrice();
            int productQuantity = quantitySpnr5.getValue();
            int productStock = product5.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn6)) {
            String productName = product6.getProductName();
            double productPrice = product6.getProductPrice();
            int productQuantity = quantitySpnr6.getValue();
            int productStock = product6.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn7)) {
            String productName = product7.getProductName();
            double productPrice = product7.getProductPrice();
            int productQuantity = quantitySpnr7.getValue();
            int productStock = product7.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn8)) {
            String productName = product8.getProductName();
            double productPrice = product8.getProductPrice();
            int productQuantity = quantitySpnr8.getValue();
            int productStock = product8.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn9)) {
            String productName = product9.getProductName();
            double productPrice = product9.getProductPrice();
            int productQuantity = quantitySpnr9.getValue();
            int productStock = product9.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn10)) {
            String productName = product10.getProductName();
            double productPrice = product10.getProductPrice();
            int productQuantity = quantitySpnr10.getValue();
            int productStock = product10.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn11)) {
            String productName = product11.getProductName();
            double productPrice = product11.getProductPrice();
            int productQuantity = quantitySpnr11.getValue();
            int productStock = product11.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        if (sourceButton.equals(addBtn12)) {
            String productName = product12.getProductName();
            double productPrice = product12.getProductPrice();
            int productQuantity = quantitySpnr12.getValue();
            int productStock = product12.getProductStock();
            
            checkItemStock(productName, productPrice, productQuantity, productStock);
        }
        initializeProducts(); 
    }    

    // Method that checks the item stock then adds it to the cart:
    public void checkItemStock(String productName, double productPrice, int productQuantity, int productStock) { 
        int stock = productStock - productQuantity;

        if (stock >= 0) {
            ProductCategory1 data = new ProductCategory1(productName, productPrice, productQuantity);
            CartList.getInstance().addData(data);

            AlertMaker.showItemAddedAlert("Chill & Swirl", "Item Added Successfully", "Proceed to your cart to view your items.");
        }
        else {
            AlertMaker.showItemOutofStockAlert("Chill & Swirl", "Out of Stock", "This item is out of stock to complete your order.");
        } 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory <Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory4= new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory8= new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
        SpinnerValueFactory <Integer> valueFactory12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99);
    
        valueFactory1.setValue(1);
        valueFactory2.setValue(1);
        valueFactory3.setValue(1);
        valueFactory4.setValue(1);
        valueFactory5.setValue(1);
        valueFactory6.setValue(1);
        valueFactory7.setValue(1);
        valueFactory8.setValue(1);
        valueFactory9.setValue(1);
        valueFactory10.setValue(1);
        valueFactory11.setValue(1);
        valueFactory12.setValue(1);

        quantitySpnr1.setValueFactory(valueFactory1);
        quantitySpnr2.setValueFactory(valueFactory2);
        quantitySpnr3.setValueFactory(valueFactory3);
        quantitySpnr4.setValueFactory(valueFactory4);
        quantitySpnr5.setValueFactory(valueFactory5);
        quantitySpnr6.setValueFactory(valueFactory6);
        quantitySpnr7.setValueFactory(valueFactory7);
        quantitySpnr8.setValueFactory(valueFactory8);
        quantitySpnr9.setValueFactory(valueFactory9);
        quantitySpnr10.setValueFactory(valueFactory10);
        quantitySpnr11.setValueFactory(valueFactory11);
        quantitySpnr12.setValueFactory(valueFactory12);

        initializeProducts();
    }
}