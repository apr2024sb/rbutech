package com.rbu.bms.repos;

import com.rbu.bms.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
