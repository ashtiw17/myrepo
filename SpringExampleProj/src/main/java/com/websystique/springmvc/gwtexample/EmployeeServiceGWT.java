package main.java.com.websystique.springmvc.gwtexample;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import main.java.com.websystique.springmvc.springexample.model.Employee;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/employeeServiceGWT")
public interface EmployeeServiceGWT extends RemoteService {
 public Employee findEmployee(int employeeId);
 public void saveEmployee(int id, String name,String ssn, LocalDate joiningDate, BigDecimal salary) throws Exception;
 public void updateEmployee(int id, String name,String ssn, LocalDate joiningDate, BigDecimal salary) throws Exception;
 public void saveOrUpdateEmployee(int id, String name,String ssn, LocalDate joiningDate, BigDecimal salary) throws Exception;
 public void deleteEmployee(int employeeId) throws Exception;
}

