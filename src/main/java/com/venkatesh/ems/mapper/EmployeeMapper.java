package com.venkatesh.ems.mapper;

import com.venkatesh.ems.dto.EmployeeDto;
import com.venkatesh.ems.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {
    public static Employee mapToEmployee(EmployeeDto employeeDto){
//        Employee employee=new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail()
//        );
//        return employee;
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail());
    }
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
    public static List<EmployeeDto> mapToEmployeeDtoList(List<Employee> employees){
        List<EmployeeDto> list=new ArrayList<>();
        for (Employee employee: employees){
            EmployeeDto employeeDto=mapToEmployeeDto(employee);
            list.add(employeeDto);
        }
        return list;
    }
}
