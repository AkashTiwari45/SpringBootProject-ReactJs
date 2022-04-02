package com.springboot.Emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Emp.entity.EmployeeEntity;

//import com.springboot.Emp.model.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, Long>
{
    
}
