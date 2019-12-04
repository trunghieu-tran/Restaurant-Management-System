package services.Controllers;

import com.sun.tools.corba.se.idl.constExpr.Or;
import model.Entity.Order;
import model.Entity.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderController {
    private static OrderController instance;

    public OrderController() {
    }

    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
    public Order getOrderForTable(int tableId) {
        return DatabaseController.getInstance().getOrderForTable(tableId);
    }

    public boolean addItemToTab(int orderId, String item) {
        return DatabaseController.getInstance().addItemToTab(orderId, item);
    }

    public boolean submitOrder(int orderId) {
        return DatabaseController.getInstance().submitOrder(orderId);
    }

    public List<Order> getOrderInQueue() {
        return DatabaseController.getInstance().getOrderInQueue();
    }

    public Map<Integer, Integer> getMapOrderToTable() {
        Map<Integer, Integer> tmp = new HashMap<>();
        Map<Order, Table> orderTableMap = DatabaseController.getInstance().getMapOrderToTable();
        for (Order o : orderTableMap.keySet()) {
            System.out.println(o.toString());
            System.out.println(orderTableMap.get(o).toString());
            tmp.put(o.getOrderID(), orderTableMap.get(o).getTableNumber());
        }
        return tmp;
    }

    public float getOrderPayment(int orderID) {
        Order o = DatabaseController.getInstance().getOrderByID(orderID);
        return o.getPayment();
    }
    public void updateChef() {
        // TODO: socket or TCP to send message to other computer
    }
}
