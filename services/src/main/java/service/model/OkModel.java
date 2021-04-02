package service.model;

import lombok.*;

@Getter
@Setter


public class OkModel {
  private String message;

  public OkModel(String message) {
    this.message = message;
  }

  public OkModel() {
  }
}
