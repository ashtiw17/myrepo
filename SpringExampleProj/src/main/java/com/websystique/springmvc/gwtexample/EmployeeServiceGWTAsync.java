package main.java.com.websystique.springmvc.gwtexample;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface EmployeeServiceGWTAsync

{

	/**
	 * 
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see com.javacodegeeks.gwtspring.shared.services.EmployeeService
	 */

	void findEmployee(int employeeId, AsyncCallback callback);

	/**
	 * 
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see com.javacodegeeks.gwtspring.shared.services.EmployeeService
	 */

	void saveEmployee(int id, String name, String ssn, LocalDate joiningDate,
			BigDecimal salary, AsyncCallback callback);

	/**
	 * 
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see com.javacodegeeks.gwtspring.shared.services.EmployeeService
	 */

	void updateEmployee(int id, String name, String ssn, LocalDate joiningDate,
			BigDecimal salary, AsyncCallback callback);

	/**
	 * 
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see com.javacodegeeks.gwtspring.shared.services.EmployeeService
	 */

	void saveOrUpdateEmployee(int id, String name, String ssn,
			LocalDate joiningDate, BigDecimal salary, AsyncCallback callback);

	/**
	 * 
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see com.javacodegeeks.gwtspring.shared.services.EmployeeService
	 */

	void deleteEmployee(int employeeId, AsyncCallback callback);

	/**
	 * 
	 * Utility class to get the RPC Async interface from client-side code
	 */

	public static final class Util

	{

		private static EmployeeServiceGWTAsync instance;

		public static final EmployeeServiceGWTAsync getInstance()

		{

			if (instance == null)

			{

				instance = (EmployeeServiceGWTAsync) GWT
						.create(EmployeeServiceGWT.class);

				ServiceDefTarget target = (ServiceDefTarget) instance;

				target.setServiceEntryPoint(GWT.getModuleBaseURL()
						+ "springGwtServices/employeeServiceGWT");

			}

			return instance;

		}

		private Util()

		{

			// Utility class should not be instanciated

		}

	}

}