package com.venkatesh.ems.service;

import com.venkatesh.ems.dto.EmployeeDto;
import com.venkatesh.ems.entity.Employee;

public interface EmployeeService {
    Employee addEmployee(EmployeeDto employeeDto);
}
