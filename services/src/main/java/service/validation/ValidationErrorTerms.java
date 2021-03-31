package service.validation;

import java.util.Map;

public class ValidationErrorTerms {
  public ValidationErrorTerms() {
  }

  public static final String KEY_NOT_UNIQUE = "error of unique";
  public static final String INVALID_ROLE_OF_USER = "error creating user with museum id and status user" +
    "or null museum id and status museum";

  private static final Map<String, String> ERRORS = Map.ofEntries(
    Map.entry(KEY_NOT_UNIQUE, "Пользователь с введёнными данными уже сузествует"),
    Map.entry(INVALID_ROLE_OF_USER, "Пользователь с введёнными данными уже существует"));

  public static String getMessageByCode(String code) {
    return ERRORS.get(code);
  }
}
