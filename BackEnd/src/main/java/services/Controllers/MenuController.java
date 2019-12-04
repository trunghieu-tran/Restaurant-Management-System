package services.Controllers;


import model.Entity.Category;
import model.Entity.Item;
import model.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;

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
}
