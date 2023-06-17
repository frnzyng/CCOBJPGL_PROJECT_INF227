package model;

// This class is mostly used as a type
// Used in CheckoutController, OrderHistoryController, and OrdersController
public class Order {
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String orderDate;
    private String orderTime;

    public Order(String productName, double productPrice, int productQuantity, String orderDate, String orderTime) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(double price) {
        this.productPrice = price;
    }
    
    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}