package model.Entity;

public class Menu {
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
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
