package com.springboot.Emp.services;

import java.util.List;

import com.springboot.Emp.model.Employee;

public interface EmployeeService 
{

	Employee createEmployee(Employee emp);

	List<Employee> getAllEmployees();

	boolean deleteEmployee(Long id);

	Employee getEmployeeById(Long id);

	Employee updateEmployee(Long id, Employee employee);

}
