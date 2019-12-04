package model.Entity;

import java.util.List;

public class Menu {
    private List<Category> categories;

    public Menu(List<Category> listCat) {
        this.categories = listCat;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Item getItemByID(String item) {
        for (Category cat : categories) {
            for (Item it : cat.getItems())
                if (it.getDescription().equals(item))
                    return it;
        }
        return null;
    }
    @Override
    public String toString() {
        String result = "";
        for (Category cur : this.categories) {
            result.concat(cur.toString());
        }
        return result;
    }
}
