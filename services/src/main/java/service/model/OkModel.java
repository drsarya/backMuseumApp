package service.model;

import org.immutables.value.Value;


@Value.Immutable
//@JsonDeserialize(builder = ImmutableOkModel.Builder.class)
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
