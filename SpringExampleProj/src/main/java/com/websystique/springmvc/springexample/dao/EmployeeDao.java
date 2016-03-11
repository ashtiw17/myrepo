package main.java.com.websystique.springmvc.springexample.dao;
 
import java.util.List;

import main.java.com.websystique.springmvc.springexample.model.Employee;

public interface EmployeeDao {
 
    Employee findById(int id);
 
    void saveEmployee(Employee employee);
     
    void deleteEmployeeBySsn(String ssn);
     
    List<Employee> findAllEmployees();
 
    Employee findEmployeeBySsn(String ssn);
 
}