package model.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private LocalDate placed;
    private boolean isReady;
    private List<Item> orderedItems;

    public Order(int id) {
        orderID = id;
        placed = LocalDate.now();
        isReady = false;
        orderedItems = new ArrayList<>();
    }
    public int getOrderID() {
        return orderID;
    }

    public LocalDate getPlaced() {
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

    public void setPlaced(LocalDate placed) {
        this.placed = placed;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addItem(Item it) {
        orderedItems.add(it);
    }

    public float getPayment() {
        float total = 0;
        for (Item it : orderedItems) {
            total += it.getPrice();
        }
        return total;
    }
}
