package services.Controllers;


import model.Entity.Category;
import model.Entity.Item;
import model.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private Menu menu;

    private static MenuController instance;

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }


    @Autowired
    public MenuController() {
        menu = DatabaseController.getInstance().getMenu();
    }

    public Menu getMenu() {
        return menu;
    }

    public List<String> getCategoryName() {
        List<String> res = new ArrayList<>();
        for (Category c : menu.getCategories()) {
            res.add(c.getName());
        }
        return res;
    }

    public Category getCategoryByID(String id) {
        for (Category c : menu.getCategories()) {
            if (c.getName().equals(id)) return c;
        }
        return null;
    }
}
