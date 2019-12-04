package model.Entity;

public class Item {
    private String description;
    private float price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return description + ":  $" + String.valueOf(price);
    }
}
