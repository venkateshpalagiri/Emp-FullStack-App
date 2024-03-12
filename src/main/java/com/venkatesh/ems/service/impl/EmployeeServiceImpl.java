package com.venkatesh.ems.service.impl;

import com.venkatesh.ems.dto.EmployeeDto;
import com.venkatesh.ems.entity.Employee;
import com.venkatesh.ems.exception.ResourceNotFoundException;
import com.venkatesh.ems.mapper.EmployeeMapper;
import com.venkatesh.ems.repository.EmployeeRepository;
import com.venkatesh.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee=employeeRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with the given id "+id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> list=employeeRepository.findAll();
//        return EmployeeMapper.mapToEmployeeDtoList(list);
        return list.stream()
                .map((employee -> EmployeeMapper.mapToEmployeeDto(employee)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee=employeeRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with given Id:"+id));
        if(Objects.nonNull(employeeDto.getFirstName())&& !"".equalsIgnoreCase(employeeDto.getFirstName())){
            employee.setFirstName(employeeDto.getFirstName());
        }
        if(Objects.nonNull(employeeDto.getLastName())&&!"".equalsIgnoreCase(employeeDto.getLastName())){
            employee.setLastName(employeeDto.getLastName());
        }
        if(Objects.nonNull(employeeDto.getEmail())&&!"".equalsIgnoreCase(employeeDto.getEmail())){
            employee.setEmail(employeeDto.getEmail());
        }

//        Employee savedEmployee=employeeRepository
//                .save(new Employee(employeeDto.getId(),employeeDto.getFirstName(),employeeDto.getLastName(),employeeDto.getEmail()));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

//        employeeRepository.deleteById(id);
//                        OR
        Employee employee=employeeRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with the given id:"+id));
        employeeRepository.delete(employee);

    }
}
