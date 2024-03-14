package com.venkatesh.ems.advise;

import com.venkatesh.ems.dto.CustomErrorResponse;
import com.venkatesh.ems.dto.GlobalErrorCode;
import com.venkatesh.ems.exception.EmployeeNotFound;
import com.venkatesh.ems.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        CustomErrorResponse customErrorResponse= CustomErrorResponse
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(exception.getMessage())
                .errorCode(GlobalErrorCode.EMPLOYEE_NOT_FOUND)
                .build();
        return ResponseEntity.internalServerError().body(customErrorResponse);
    }
    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<?> handleEmployeeNotFound(EmployeeNotFound employeeNotFound){
        CustomErrorResponse customErrorResponse=CustomErrorResponse
                .builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(employeeNotFound.getMessage())
                .errorCode(GlobalErrorCode.EMPLOYEE_NOT_FOUND)
                .build();
        return ResponseEntity.internalServerError().body(customErrorResponse);
    }
}
