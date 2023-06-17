package model;

// This class is used in the LoginController to get the logged-in username and display it in the home page
// It is also used in the CheckoutController to set the order details of the customer
public class Customer {
    private static Customer instance;
    private String username;
    private String password;
    private String customerName;
    private String contactNumber;
    private String deliveryAddress;
    private String paymentMethod;
    private boolean rating = false;

    public Customer(String customerName, String contactNumber, String deliveryAddress, String paymentMethod) {
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
    }

    public Customer() {
        // Wag alisin, for some reason kailangan siya 
    }

    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String number) {
        this.contactNumber = number;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String address) {
        this.deliveryAddress = address;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean getRatings() {
        return this.rating;
    }

    public void setRatings(Boolean rating) {
        this.rating = rating;
    }

    public boolean isRated() {
        return this.rating;
    }
}
