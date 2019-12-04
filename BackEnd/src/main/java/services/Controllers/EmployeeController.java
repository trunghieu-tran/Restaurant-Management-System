package services.Controllers;

import model.Entity.Employee;
import model.Entity.Waiter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeController {
    private static ArrayList<Employee> employees;

    @Autowired
    public EmployeeController() {
        employees = new ArrayList<>();
        try {
            File file = new File(".\\src\\main\\resources\\Employees.txt");
            Scanner input = new Scanner(file);
            String cur = null;
            int spaceIndex;
            while (input.hasNextLine()) {
                cur = input.nextLine();
                spaceIndex = cur.indexOf(" ");
                employees.add(new Employee(cur.substring(0, spaceIndex),
                        cur.substring(spaceIndex+1, cur.length())));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    public static ArrayList<String> getEmployees() {
        ArrayList<String> employeeList = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeList.add(employees.get(i).toString());
        }
        return employeeList;
    }

    public static boolean validateUser(String id, String pwd) {
        for (Employee cur : employees) {
            if (cur.getId().equals(id) && cur.getPwd().equals(pwd)) {
                return true;
            }
        }
        return false;
    }
}
