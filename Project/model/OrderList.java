package model;

import java.util.ArrayList;
import java.util.List;

// This class is for listing the orders placed by the user
public class OrderList {
    private static OrderList instance;
    private List<Product> dataList;

    private OrderList() {
        dataList = new ArrayList<>();
    }

    public static OrderList getInstance() {
        if (instance == null) {
            instance = new OrderList();
        }
        return instance;
    }

    public List<Product> getDataList() {
        return dataList;
    }

    public void addData(Product data) {
        dataList.add(data);
    }

    public void clearData() {
        dataList.clear();
    }

    public boolean isOrderListEmpty() {
        return dataList.isEmpty();
    }
}
