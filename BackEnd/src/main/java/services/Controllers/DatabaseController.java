package services.Controllers;

import model.Entity.*;
import utils.Config;
import utils.FileReader;

import java.util.ArrayList;
import java.util.List;


public class DatabaseController {

    private static DatabaseController instance = null;
    private List<Employee> employees;

    public DatabaseController() {
        // initialize data here
        employees = initEmployeeData();
    }

    public static DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    private List<Employee> initEmployeeData() {
        List<Employee> employees = new ArrayList<>();

        String data = FileReader.readStringFromFile(Config.UsingMacOS ? Config.EmployeeDataFileMacOS : Config.EmployeeDataFileWindows);

        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(" ");
            employees.add(new Employee(parts[0], parts[1]));
        }

        return employees;
    }

    public Employee getEmployeeInfo(String id) {
        return null;
    }
    
    public Table[] getTableList() {
        return null;
    }

    public Table getTableData(int tableId) {
        return null;
    }

    public Order getOrderForTable(int tableId) {
        return null;
    }

    public Order createNewOrder(int tableId) {
        return null;
    }

    public Category[] getMenu() {
        return null;
    }

    public Item[] getItemsInCategory(int categoryId) {
        return null;
    }

    public boolean updateOrder(Order order) {
        return false;
    }



}
