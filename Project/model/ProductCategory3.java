package model;

public class ProductCategory3 extends Product {

    // Nag-nitialize ako dito para hindi magreset yung values ng stock kapag nagchange ng page
    // Nagre-reset kasi yung values kapag sa mismong controller nag-initialize
    public static int productStock1 = 99;
    public static int productStock2 = 99;
    public static int productStock3 = 99;
    public static int productStock4 = 99;
    public static int productStock5 = 99;
    public static int productStock6 = 99;
    public static int productStock7 = 99;
    public static int productStock8 = 99;

    public ProductCategory3(String productName, double productPrice, int productquantity) {
        super(productName, productPrice, productquantity);
        //TODO Auto-generated constructor stub
    }
    
}
