package model;

import java.util.ArrayList;
import java.util.List;

// This class is for listing the previous orders placed by the user
public class OrderHistory {
    private static OrderHistory instance;
    private List<Order> dataList;

    private OrderHistory() {
        dataList = new ArrayList<>();
    }

    public static OrderHistory getInstance() {
        if (instance == null) {
            instance = new OrderHistory();
        }
        return instance;
    }

    public List<Order> getDataList() {
        return dataList;
    }

    public void addData(Order data) {
        dataList.add(data);
    }

    public void clearData() {
        dataList.clear();
    }

    public boolean isOrderListEmpty() {
        return dataList.isEmpty();
    }
}
