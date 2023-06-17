package model;

import java.util.ArrayList;
import java.util.List;

// This class is for listing the order details of the customer (contact name, contact number, address)
public class OrderDetails {
    private static OrderDetails instance;
    private List<Customer> dataList;

    private OrderDetails() {
        dataList = new ArrayList<>();
    }

    public static OrderDetails getInstance() {
        if (instance == null) {
            instance = new OrderDetails();
        }
        return instance;
    }

    public List<Customer> getDataList() {
        return dataList;
    }

    public void addData(Customer data) {
        dataList.add(data);
    }

    public void remove(Customer selectedData) {
        dataList.remove(selectedData);
    }
}