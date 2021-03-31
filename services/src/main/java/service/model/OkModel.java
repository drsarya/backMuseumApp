package service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class OkModel {
  public String message ;

  public OkModel(String getMessage) {
    this.message = getMessage;
  }
}
