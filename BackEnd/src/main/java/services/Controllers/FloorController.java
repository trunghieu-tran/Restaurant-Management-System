package services.Controllers;

import model.Entity.Table;

import java.util.List;

public class FloorController {
    private static FloorController instance;
    private List<Table> tables;

    public FloorController() {
        tables = DatabaseController.getInstance().getTables();
    }
    public static FloorController getInstance() {
        if (instance == null) {
            instance = new FloorController();
        }
        return instance;
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table getTableInfo(int tableId) {
        return null;
    }
}
