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
  ResponseEntity<List<ErrorModel>> handleConstraintViolationException(DataIntegrityViolationException e) {
    List<ErrorModel> errorModels = new ArrayList<>();
    errorModels.add(new ErrorModel(ValidationErrorTerms.KEY_NOT_UNIQUE, ValidationErrorTerms.getMessageByCode(ValidationErrorTerms.KEY_NOT_UNIQUE)));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModels);
  }
  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<List<ErrorModel>> handleArgumentException(IllegalArgumentException e) {
    List<ErrorModel> errorModels = new ArrayList<>();
    errorModels.add(new ErrorModel(ValidationErrorTerms.KEY_NOT_UNIQUE, e.getMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModels);
  }

}
