package main.java.com.websystique.springmvc.gwtexample;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import main.java.com.websystique.springmvc.springexample.dao.EmployeeDao;
import main.java.com.websystique.springmvc.springexample.model.Employee;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeServiceGWT")
public class EmployeeServiceImpl implements EmployeeServiceGWT {
	@Autowired
	private EmployeeDao employeeDAO;

	@PostConstruct
	public void init() throws Exception {
	}

	@PreDestroy
	public void destroy() {
	}

	public Employee findEmployee(int employeeId) {

		return employeeDAO.findById(employeeId);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveEmployee(int id, String name,
			String ssn, LocalDate joiningDate, BigDecimal salary) throws Exception {

		Employee employeeDTO = employeeDAO.findById(id);

		if (employeeDTO == null) {
			employeeDTO = new Employee(id, name,ssn, joiningDate, salary);
			employeeDAO.saveEmployee(employeeDTO);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateEmployee(int id, String name,
			String ssn, LocalDate joiningDate, BigDecimal salary) throws Exception {

		Employee employeeDTO = employeeDAO.findById(id);

		if (employeeDTO != null) {
			employeeDTO.setName(name);
			employeeDTO.setSsn(ssn);
			employeeDTO.setJoiningDate(joiningDate);
			employeeDTO.setSalary(salary);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteEmployee(int employeeId) throws Exception {

		Employee employeeDTO = employeeDAO.findById(employeeId);

		if (employeeDTO != null)
			employeeDAO.deleteEmployeeBySsn(employeeDTO.getSsn());

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveOrUpdateEmployee(int id, String name,
			String ssn, LocalDate joiningDate, BigDecimal salary) throws Exception {

		Employee employeeDTO = new Employee(id, name,ssn, joiningDate, salary);

		employeeDAO.saveEmployee(employeeDTO);

	}

}
