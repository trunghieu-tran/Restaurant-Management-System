package model.Entity;

public class Category {
    String name;
    Item[] items;

    public Category() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
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
