package model;

import java.util.ArrayList;
import java.util.List;

// This class is for listing the items/products added by the user
public class CartList {
    private static CartList instance;
    private List<Product> dataList;

    private CartList() {
        dataList = new ArrayList<>();
    }

    public static CartList getInstance() {
        if (instance == null) {
            instance = new CartList();
        }
        return instance;
    }

    public List<Product> getDataList() {
        return dataList;
    }

    public void addData(Product data) {
        dataList.add(data);
    }

    public void remove(Product selectedData) {
        dataList.remove(selectedData);
    }

    public void clearData() {
        dataList.clear();
    }
}