package services;

import model.Entity.Category;
import model.Entity.Item;
import model.Entity.Order;
import model.Entity.Table;
import org.springframework.web.bind.annotation.RequestParam;
import services.Controllers.EmployeeController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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