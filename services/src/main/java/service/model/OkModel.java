package service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;


@Value.Immutable
@JsonDeserialize(builder = ImmutableOkModel.Builder.class)

public class OkModel {
  private String message;

  public OkModel(String message) {
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
  public OkModel() {
  }
}
