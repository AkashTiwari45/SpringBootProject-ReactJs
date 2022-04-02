package com.springboot.Emp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Emp.entity.EmployeeEntity;
import com.springboot.Emp.model.Employee;
import com.springboot.Emp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private EmployeeRepository employeeRepository ;


	@Override
	public Employee createEmployee(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();

		BeanUtils.copyProperties(employee, employeeEntity);
		employeeRepository.save(employeeEntity);
		return employee;
	}


	@Override
	public List<Employee> getAllEmployees() {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

		List<Employee> employees = employeeEntities
				.stream()
				.map(emp -> new Employee(
						emp.getId(),
						emp.getFirstName(),
						emp.getLastName(),
						emp.getEmailId()))
				.collect(Collectors.toList());
		return employees;
	}

	 @Override
	    public boolean deleteEmployee(Long id) {
	        EmployeeEntity employee = employeeRepository.findById(id).get();
	        employeeRepository.delete(employee);
	        return true;
	    }


	@Override
	public Employee getEmployeeById(Long id) 
	{
		// getting data from database and passing back to the controller
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		Employee employee = new Employee(id, null, null, null);
		BeanUtils.copyProperties(employeeEntity, employee);
		return employee;
	}


	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
		// the mistake 
		employeeEntity.setEmailId(employee.getEmailId());
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
	    
		
	    employeeRepository.save(employeeEntity);
		return employee;
	}



}
