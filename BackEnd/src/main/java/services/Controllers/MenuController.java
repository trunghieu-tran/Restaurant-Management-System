package services.Controllers;


import model.Entity.Category;
import model.Entity.Item;
import model.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private static MenuController instance;

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }


    @Autowired
    public MenuController() {
    }

    public Menu getMenu() {
        return DatabaseController.getInstance().getMenu();
    }

    public List<String> getCategoryName() {
        return DatabaseController.getInstance().getCategoryName();
    }

    public Category getCategoryByID(String id) {
        return DatabaseController.getInstance().getCategoryByID(id);
    }
}
