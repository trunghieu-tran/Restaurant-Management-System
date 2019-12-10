package services.Controllers;

import model.Entity.*;
import utils.Config;
import utils.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseController {

    private static DatabaseController instance = null;
    private List<Employee> employees;
    private Menu menu;
    private Map<Order, Table> mapOrderToTable;
    private Map<Table, Order> mapTableToOrder;
    private List<Table> tables;
    private List<Order> orders;
    private List<Order> orderInQueue;
    private static String newLine = "\r\n";

    public DatabaseController() {
        // initialize data here
        employees = initEmployeeData();
        menu = initMenu();
        tables = initTableData();
        mapOrderToTable = new HashMap<>();
        mapTableToOrder = new HashMap<>();
        orders = new ArrayList<>();
        orderInQueue = new ArrayList<>();
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

    public Map<Table, Order> getMapTableToOrder() {
        return mapTableToOrder;
    }

    private List<Table> initTableData() {
        List<Table> tables = new ArrayList<>();
        String data = FileReader.readStringFromFile(Config.UsingMacOS ? Config.TableDataFileMacOS : Config.TableDataFileWindows);

        String[] lines = data.split(newLine);
        for (String line : lines) {
            Table newTable = new Table(Integer.parseInt(line));
            tables.add(newTable);
        }

        return tables;
    }

    private List<Employee> initEmployeeData() {
        List<Employee> employees = new ArrayList<>();

        String data = FileReader.readStringFromFile(Config.UsingMacOS ? Config.EmployeeDataFileMacOS : Config.EmployeeDataFileWindows);

        String[] lines = data.split(newLine);
        for (String line : lines) {
            String[] parts = line.split(" ");
            employees.add(new Employee(parts[0], parts[1]));
        }

        return employees;
    }

    public Menu getMenu() {
        return menu;
    }

    public Map<Order, Table> getMapOrderToTable() {
        return mapOrderToTable;
    }

    public List<Table> getTableList() {
        return tables;
    }

    private Menu initMenu() {
        List<Category> categories = new ArrayList<>();
        List<Item> allItems = new ArrayList<>();

        String itemData = FileReader.readStringFromFile(Config.UsingMacOS ? Config.ItemDataFileMacOS : Config.ItemDataFileWindows);
        String[] lines = itemData.split(newLine);
        for (String line : lines) {
            String[] parts = line.split(",");
            Item newItem = new Item(parts[0], Float.parseFloat(parts[1]));
            allItems.add(newItem);
        }

        String categoryData = FileReader.readStringFromFile(Config.UsingMacOS ? Config.CategoryDataFileMacOS : Config.CategoryDataFileWindows);
        lines = categoryData.split(newLine);
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
        for (Employee cur : employees) {
            if (cur.getId().equals(id)) {
                return cur;
            }
        }
        return null;
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

    public Table getTableData(int tableId) {
        for (Table tb : tables)
            if (tb.getTableNumber() == tableId)
                return tb;
        return null;
    }

    public Order getOrderForTable(int tableId) {
        Table tb = getTableData(tableId);
        if (!mapTableToOrder.containsKey(tb)) {
            Order newOrder = createNewOrder();
            mapTableToOrder.put(tb, newOrder);
            mapOrderToTable.put(newOrder, tb);
        }
        return mapTableToOrder.get(tb);
    }

    public Order getOrderByID(int orderID) {
        for (Order o : orders)
            if (o.getOrderID() == orderID) return o;
        return null;
    }

    public Order createNewOrder() {
        Order newOrder = new Order(orders.size());
        orders.add(newOrder);
        return newOrder;
    }

    public boolean addItemToTab(int orderId, String item) {
        Order o = getOrderByID(orderId);
        Item it = menu.getItemByID(item);
        if (o == null || it == null) return false;
        o.addItem(it);
        return true;
    }

    public boolean submitOrder(int orderId) {
        Order o = getOrderByID(orderId);
        if (o == null) { return false; }
        mapOrderToTable.get(o).setStatus(TableStatus.Occupied);
        orderInQueue.add(o);
        return true;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getOrderInQueue() {
        return orderInQueue;
    }
}
