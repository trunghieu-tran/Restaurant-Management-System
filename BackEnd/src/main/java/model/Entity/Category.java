package model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    String name;
    List<Item> items;

    public Category(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public void addItem(Item it) {
        items.add(it);
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Item>  getItems() {
        return items;
    }

    public void setItems(List<Item>  items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String result = name + ":\n";
        for (Item cur : this.items) {
            result += cur.toString() + ",\t";
        }
        result.concat("\n");
        return result;
    }
}
