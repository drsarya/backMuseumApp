package museum.api;

import museum.exception.ErrorModel;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import validation.ValidationErrorTerms;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerApp {

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<List<ErrorModel>> handleArgumentException(IllegalArgumentException e) {
    List<ErrorModel> errorModels = Arrays.asList(new ErrorModel(ValidationErrorTerms.KEY_NOT_UNIQUE, e.getMessage()));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModels);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<List<ErrorModel>> handleConstraintViolationException(ConstraintViolationException e) {
    final Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
    final var errorModels = errors.stream()
      .map(err -> new ErrorModel(err.getMessage(), ValidationErrorTerms.getMessageByCode(err.getMessage())))
      .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModels);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  ResponseEntity<List<ErrorModel>> handleConstraintViolationException(DataIntegrityViolationException e) {
    List<ErrorModel> errorModels = Arrays.asList(new ErrorModel(ValidationErrorTerms.KEY_NOT_UNIQUE, ValidationErrorTerms.getMessageByCode(ValidationErrorTerms.KEY_NOT_UNIQUE)));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModels);
  }
}
