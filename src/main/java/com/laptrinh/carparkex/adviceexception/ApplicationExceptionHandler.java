package com.laptrinh.carparkex.adviceexception;

import com.laptrinh.carparkex.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String > errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public Map<String, String> handleBusinessException(EmployeeNotFoundException ex){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("errorMessage", ex.getMessage());
        return errMap;
    }

    @ExceptionHandler(ParkingLotNotFoundException.class)
    public Map<String, String> handleParkingLotNotFoundException(ParkingLotNotFoundException e){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("errMsg", e.getMessage());
        return errMap;
    }
    @ExceptionHandler(TripIdExisted.class)
    public Map<String, String> handleTripExistedException(TripIdExisted ex){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("errMsg", ex.getMessage());
        return errMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TripNotFoundException.class)
    public Map<String, String> handleTripNotFoundException(TripNotFoundException e){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("errMsg", e.getMessage());
        return errMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InCorrectInputException.class)
    public Map<String, String> handleInputIncorrectException(InCorrectInputException e){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("errMsg", e.getMessage());
        return errMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CarNotFoundException.class)
    public String handleCarNotFoundException(CarNotFoundException e){
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookOfficeNotFoundException.class)
    public Map<String, String> handleBookOfficeNotFoundException(BookOfficeNotFoundException ex){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("errMsg", ex.getMessage());
        return errMap;
    }
}
