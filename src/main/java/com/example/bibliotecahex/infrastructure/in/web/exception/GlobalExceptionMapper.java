package com.example.bibliotecahex.infrastructure.in.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionMapper {

   @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
   public ResponseEntity<Map<String, Object>> handleIllegalExceptions(RuntimeException ex, HttpServletRequest request) {
       Map<String, Object> response = new LinkedHashMap<>();
       response.put("timestamp", ZonedDateTime.now());
       response.put("status", HttpStatus.BAD_REQUEST.value());
       response.put("error", "Bad Request");
       response.put("message", ex.getMessage());
       response.put("path", request.getRequestURI());

       return ResponseEntity.badRequest().body(response);
   }

   @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<ErrorResponse> handleBusinessException(RuntimeException ex, HttpServletRequest request) {
       ErrorResponse errorResponse = new ErrorResponse(
               "01",
               "Error de negocio",
               ex.getMessage()
       );

       return ResponseEntity.badRequest().body(errorResponse);
   }

   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
       ErrorResponse errorResponse = new ErrorResponse(
               "03",
               "Ocurrió un error en el servidor",
               ex.getMessage()
       );

       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
       Map<String, String> errors = new HashMap<>();

       ex.getBindingResult().getAllErrors().forEach(error -> {
           String fieldName = ((FieldError) error).getField();
           String message = error.getDefaultMessage();
           errors.put(fieldName, message);
       });

       return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<Map<String, String>> handleConstraintViolations(ConstraintViolationException ex) {
       Map<String, String> errors = new HashMap<>();
       ex.getConstraintViolations().forEach(cv -> {
           String path = cv.getPropertyPath().toString();
           String field = path.substring(path.lastIndexOf('.') + 1);
           errors.put(field, cv.getMessage());
       });
       return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }
}