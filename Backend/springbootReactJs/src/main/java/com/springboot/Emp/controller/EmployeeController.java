package com.springboot.Emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Emp.entity.EmployeeEntity;
import com.springboot.Emp.model.Employee;
import com.springboot.Emp.repository.EmployeeRepository;
import com.springboot.Emp.services.EmployeeService;

// this annotation is used to solve the cors policy issue eror
@CrossOrigin(origins = "http://localhost:3000/" )

@RestController
@RequestMapping("/MyWebPro")
public class EmployeeController 
{
	@Autowired
	private final EmployeeService employeeService ;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp)
	{
		return employeeService.createEmployee(emp) ;
	}

	// getting all empoyees rest api
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}

	//delete api
	@DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id) {
        boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
	// update api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = null;
		employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}


	// creating method for updating data in database based on the id provided
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
			@RequestBody Employee employee) {
		employee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(employee);
	}


}
