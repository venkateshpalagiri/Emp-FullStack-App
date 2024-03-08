package com.venkatesh.ems.service.impl;

import com.venkatesh.ems.dto.EmployeeDto;
import com.venkatesh.ems.entity.Employee;
import com.venkatesh.ems.mapper.EmployeeMapper;
import com.venkatesh.ems.repository.EmployeeRepository;
import com.venkatesh.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee=new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
       Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
