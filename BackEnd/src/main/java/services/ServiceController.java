package services;

import model.Entity.*;
import org.springframework.web.bind.annotation.RequestParam;
import services.Controllers.EmployeeController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import services.Controllers.FloorController;
import services.Controllers.MenuController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiceController {
    //    http://localhost:8080,
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // http://localhost:8080/employees
    @RequestMapping("/employees")
    public ArrayList<String> getEmployees() {
        return EmployeeController.getInstance().getEmployees();
    }

    // http://localhost:8080/login?id={id}&pwd={password}
    @RequestMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pwd) {
        if (EmployeeController.validateUser(id, pwd)) {
            return "Login successful!";
        }
        return "Error: Invalid credentials";
    }

    // http://localhost:8080/allitems
    @RequestMapping("/allitems")
    public ArrayList<Item> getItems() {
        return null;
    }


    // http://localhost:8080/menu
    @RequestMapping("/menu")
    public Menu getMeunu() {
        return MenuController.getInstance().getMenu();
    }


    // http://localhost:8080/allcategories
    @RequestMapping("/allcategories")
    public List<Category> getCategories() {
        return MenuController.getInstance().getMenu().getCategories();
    }

    // http://localhost:8080/categoryList
    @RequestMapping("/categoryList")
    public List<String> categoryList() {
        return MenuController.getInstance().getCategoryName();
    }



    // http://localhost:8080/category?id={name}
    @RequestMapping("/category")
    public Category getCategory(@RequestParam String id) {
        return MenuController.getInstance().getCategoryByID(id);
    }

    // http://localhost:8080/floorStatus
    @RequestMapping("/floorStatus")
    public List<Table> getFloorStatus() {
        return FloorController.getInstance().getTables();
    }

    //////////////////////////////

    public Table[] getTables() {
        return null;
    }

    public Table selectTable(int tableID) {
        return null;
    }

    public Order editTab(int tableID) {
        return null;
    }

    public Category[] openMenu() {
        return null;
    }

    public Item[] selectCategory(int categoryID) {
        return null;
    }

    public boolean addItemToTab(int orderID, int itemID) {
        return false;
    }

    public boolean submitOrder(int orderID) {
        return false;
    }

    public void orderQueueUpdated() {

    }
}