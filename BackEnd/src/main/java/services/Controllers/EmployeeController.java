package services.Controllers;

import model.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    private static List<Employee> employees;
    private static EmployeeController instance;

    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }

    @Autowired
    public EmployeeController() {
        employees = DatabaseController.getInstance().getEmployees();
    }

    public ArrayList<String> getEmployees() {
        ArrayList<String> employeeList = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeList.add(employees.get(i).toString());
        }
        return employeeList;
    }

    public static boolean validateUser(String id, String pwd) {
        Employee result = DatabaseController.getInstance().getEmployeeInfo(id);
        if (result != null && result.getPwd().equals(pwd)) {
            return true;
        }
        return false;
    }
}
