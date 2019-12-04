package services.Controllers;

import model.Entity.*;
import utils.Config;
import utils.FileReader;

import java.util.ArrayList;
import java.util.List;


public class DatabaseController {

    private static DatabaseController instance = null;
    private List<Employee> employees;
    private Menu menu;

    public DatabaseController() {
        // initialize data here
        employees = initEmployeeData();
        menu = initMenu();
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

    public Menu getMenu() {
        return menu;
    }

    private Menu initMenu() {
        List<Category> categories = new ArrayList<>();
        List<Item> allItems = new ArrayList<>();

        String itemData = FileReader.readStringFromFile(Config.UsingMacOS ? Config.ItemDataFileMacOS : Config.ItemDataFileWindows);
        String[] lines = itemData.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            Item newItem = new Item(parts[0], Float.parseFloat(parts[1]));
            allItems.add(newItem);
        }

        String categoryData = FileReader.readStringFromFile(Config.UsingMacOS ? Config.CategoryDataFileMacOS : Config.CategoryDataFileWindows);
        lines = categoryData.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");

            // find category
            int idx = -1;
            for (int i = 0; i < categories.size(); ++i) {
                if (categories.get(i).getName().equals(parts[0])) {
                    idx = i;
                    break;
                }
            }
            if (idx == -1) {
                Category newCat = new Category(parts[0]);
                categories.add(newCat);
                idx = categories.size() - 1;
            }

            // find item
            for (Item it : allItems) {
                if (it.getDescription().equals(parts[1])) {
                    categories.get(idx).addItem(it);
                    break;
                }
            }
        }

        return new Menu(categories);
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

    public Item[] getItemsInCategory(int categoryId) {
        return null;
    }

    public boolean updateOrder(Order order) {
        return false;
    }



}
