package services.Controllers;


import model.Entity.Order;
import model.Entity.Table;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public void updateChef() throws IOException {
        // TODO: socket or TCP to send message to other computer
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the IP address of the other end: ");
        String input = keyboard.nextLine();
        InetAddress rcvAddr = InetAddress.getByName(input);
        SocketSender.sendMessage(rcvAddr, "Order Queue Updated");
    }
}
