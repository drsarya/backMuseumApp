package museum.api;

import museum.exception.ErrorModel;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import service.validation.ValidationErrorTerms;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerApp {
  @ExceptionHandler( DataIntegrityViolationException   .class)
  ResponseEntity<ErrorModel> handleConstraintViolationException(DataIntegrityViolationException e) {

     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorModel(ValidationErrorTerms.KEY_NOT_UNIQUE, ValidationErrorTerms.getMessageByCode(ValidationErrorTerms.KEY_NOT_UNIQUE)));
  }
  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<ErrorModel> handleArgumentException(IllegalArgumentException e) {


    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorModel(ValidationErrorTerms.KEY_NOT_UNIQUE, e.getMessage()));
  }

}
