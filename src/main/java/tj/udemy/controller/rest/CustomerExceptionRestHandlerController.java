package tj.udemy.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import tj.udemy.commons.errorhandling.CustomerErrorResponse;
import tj.udemy.commons.errorhandling.CustomerNotFoundException;

@RestController
@ControllerAdvice(annotations = RestController.class)
public class CustomerExceptionRestHandlerController {

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> customerNotFound(CustomerNotFoundException exc){

        CustomerErrorResponse errorResponse = CustomerErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(exc.getMessage())
                .timseStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> exceptionHandler(Exception exc){

        CustomerErrorResponse errorResponse = CustomerErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exc.getMessage())
                .timseStamp(System.currentTimeMillis())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
}
