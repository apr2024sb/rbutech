package com.rbu.bms.service;

import com.rbu.bms.domain.Employee;
import com.rbu.bms.model.EmployeeDTO;
import com.rbu.bms.repos.EmployeeRepository;
import com.rbu.bms.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> findAll() {
        final List<Employee> employees = employeeRepository.findAll(Sort.by("id"));
        return employees.stream()
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .toList();
    }

    public EmployeeDTO get(final Long id) {
        return employeeRepository.findById(id)
                .map(employee -> mapToDTO(employee, new EmployeeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final EmployeeDTO employeeDTO) {
        final Employee employee = new Employee();
        mapToEntity(employeeDTO, employee);
        return employeeRepository.save(employee).getId();
    }

    public void update(final Long id, final EmployeeDTO employeeDTO) {
        final Employee employee = employeeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(employeeDTO, employee);
        employeeRepository.save(employee);
    }

    public void delete(final Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeDTO mapToDTO(final Employee employee, final EmployeeDTO employeeDTO) {
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setAddress(employee.getAddress());
        return employeeDTO;
    }

    private Employee mapToEntity(final EmployeeDTO employeeDTO, final Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        return employee;
    }

}
