package model.Entity;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private Date placed;
    private boolean isReady;
    private List<Item> orderedItems;

    public int getOrderID() {
        return orderID;
    }

    public Date getPlaced() {
        return placed;
    }

    public boolean isReady() {
        return isReady;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setPlaced(Date placed) {
        this.placed = placed;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
