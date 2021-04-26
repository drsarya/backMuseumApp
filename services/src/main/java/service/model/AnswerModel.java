package service.model;

import org.immutables.value.Value;


@Value.Immutable
//@JsonDeserialize(builder = ImmutableOkModel.Builder.class)
public class AnswerModel {
  private String message;

  public AnswerModel(String message) {
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
  public AnswerModel() {
  }
}
