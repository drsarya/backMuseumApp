package validation;

import java.util.Map;

public class ValidationErrorTerms {
  public ValidationErrorTerms() {
  }

  public static final String KEY_NOT_UNIQUE = "error of unique";
  public static final String INVALID_ROLE_OF_USER = "error creating user with museum id and status user" +
    "or null museum id and status museum";
  public static final String ADDRESS_MOST_BE_SET = "address must be set";
  public static final String NOT_READABLE = "cannot deserialize value";

  public static final String NAME_MOST_BE_SET = " name must be set";
  public static final String ILLEGAL_ARGUMENT ="illegal argument" ;
  private static final Map<String, String> ERRORS = Map.ofEntries(
    Map.entry(KEY_NOT_UNIQUE, "Повторяющиеся данные"),
    Map.entry(NAME_MOST_BE_SET, "Укажите название"),
    Map.entry(ADDRESS_MOST_BE_SET, "Укажите адрес"),
    Map.entry(ILLEGAL_ARGUMENT, "Проверьте поля"),
    Map.entry(NOT_READABLE, "Невозможно преобразовать данные"),
    Map.entry(INVALID_ROLE_OF_USER, "Пользователь с введёнными данными уже существует"));

  public static String getMessageByCode(String code) {
    return ERRORS.get(code);
  }
}
