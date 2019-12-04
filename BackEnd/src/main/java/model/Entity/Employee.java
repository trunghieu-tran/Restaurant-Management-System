package model.Entity;

public class Employee {
    private String id;
    private String pwd;

    public Employee(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public String getId() { return id;  }
    public void setId(String id) {  this.id = id;   }
    public String getPwd() {    return pwd; }
    public void setPwd(String pwd) {    this.pwd = pwd; }

    @Override
    public String toString() {
        return "ID: " + this.id + "\tPassword: " + this.pwd;
    }
}
