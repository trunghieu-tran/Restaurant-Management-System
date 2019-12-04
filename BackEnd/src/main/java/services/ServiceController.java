package services;

import model.Entity.*;
import org.springframework.web.bind.annotation.RequestParam;
import services.Controllers.EmployeeController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import services.Controllers.MenuController;

import java.util.ArrayList;

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

    // http://localhost:8080/employees
    @RequestMapping("/items")
    public ArrayList<Item> getItems() {
        return null;
    }


    // http://localhost:8080/menu
    @RequestMapping("/menu")
    public Menu getMeunu() {
        return MenuController.getInstance().getMenu();
    }


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