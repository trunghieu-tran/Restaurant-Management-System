package model.Entity;

public abstract class Employee {
    private String id;
    private String pwd;

    public Employee(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
