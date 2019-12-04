package services.Controllers;

import model.Entity.Order;

public class OrderController {
    public Order getOrderForTable(int tableId) {
        // TODO: get current order for table with given ID
        return null;
    }

    public boolean addItemToTab(int orderId, int itemId) {

        return true;
    }

    public boolean submitOrder(int orderId) {

        return true;
    }

    public void updateChef() {
        // TODO: socket or TCP to send message to other computer
    }
}
