package services.Controllers;

import model.Entity.Table;

import java.util.List;

public class FloorController {
    private static FloorController instance;

    public FloorController() {
    }
    public static FloorController getInstance() {
        if (instance == null) {
            instance = new FloorController();
        }
        return instance;
    }

    public List<Table> getTables() {
        return DatabaseController.getInstance().getTables();
    }

    public Table getTableInfo(int tableId) {
        return DatabaseController.getInstance().getTableData(tableId);
    }
}
