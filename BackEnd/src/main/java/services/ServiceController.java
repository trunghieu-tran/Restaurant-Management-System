package services;

import model.Entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import services.Controllers.EmployeeController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import services.Controllers.FloorController;
import services.Controllers.MenuController;
import services.Controllers.OrderController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceController {

    // to fix "No 'Access-Control-Allow-Origin' header is present on the requested resource." error
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

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

    // http://localhost:8080/table?id={id}
    @RequestMapping("/table")
    public Table selectTable(@RequestParam int id) {
        return FloorController.getInstance().getTableInfo(id);
    }

    // http://localhost:8080/editTableOrder?id={id}
    @RequestMapping("/tableOrder")
    public Order editTableOrder(@RequestParam int id) {
        return OrderController.getInstance().getOrderForTable(id);
    }

    // http://localhost:8080/addItemToOrder?orderID={order}&item={item}
    @RequestMapping("/addItemToOrder")
    public boolean addItemToOrder(@RequestParam int orderID, String item) {
        return OrderController.getInstance().addItemToTab(orderID, item);
    }

    // http://localhost:8080/submitOrder?orderID={id}
    @RequestMapping("/submitOrder")
    public boolean submitOrder(@RequestParam int orderID) {
        return OrderController.getInstance().submitOrder(orderID);
    }

    // http://localhost:8080/orderPayment?orderID={id}
    @RequestMapping("/orderPayment")
    public float orderPayment(@RequestParam int orderID) {
        return OrderController.getInstance().getOrderPayment(orderID);
    }

    // http://localhost:8080/orderInQueue
    @RequestMapping("/orderInQueue")
    public List<Order> getOrderInqueue() {
        return OrderController.getInstance().getOrderInQueue();
    }

    @RequestMapping("/mapOrderToTable")
    public Map<Integer, Integer> mapOrderToTable() {
        return OrderController.getInstance().getMapOrderToTable();
    }

    public void orderQueueUpdated() {

    }
}