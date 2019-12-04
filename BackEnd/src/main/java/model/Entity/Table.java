package model.Entity;

enum TableStatus {
    Available, Occupied, Dirty
};

public class Table {
    private int tableNumber;
    private TableStatus status;

    public Table(int id) {
        this.tableNumber = id;
        status = TableStatus.Available;
    }

    public int getTableNumber() {   return this.tableNumber;    }
    public void setTableNumber(int tableNumber) {   this.tableNumber = tableNumber; }
    public TableStatus getStatus() {    return status;  }
    public void setStatus(TableStatus status) { this.status = status;   }
}
