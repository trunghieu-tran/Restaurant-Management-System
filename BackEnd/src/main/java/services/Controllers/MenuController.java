package services.Controllers;


import model.Entity.Category;
import model.Entity.Item;
import model.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuController {
    private Menu menu;

    @Autowired
    public MenuController() {

    }

    public Category[] openMenu() {
        return menu.getCategories();
    }

    public Item[] getItemsInCategory(int categoryID) {
        return menu.getCategories()[categoryID].getItems();
    }
}
