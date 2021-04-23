package validation;

import java.util.Map;

public class ValidationErrorTerms {
  public ValidationErrorTerms() {
  }

  public static final String KEY_NOT_UNIQUE = "error of unique";
  public static final String INVALID_ROLE_OF_USER = "error creating user with museum id and status user" +
    "or null museum id and status museum";
  public static final String ADDRESS_MOST_BE_SET = " address must be set";
  public static final String DESCRIPTION_MOST_BE_SET = "description must be set";
  public static final String DATE_MOST_BE_SET = "date must be set";
  public static final String NOT_READABLE = "cannot deserialize value";
  public static final String NAME_MOST_BE_SET = "name must be set";
  public static final String ILLEGAL_ARGUMENT = "illegal argument";
  public static final String EXHIBITION_NOT_EXIST = "non-existent exhibition";
  public static final String MUSEUM_NOT_EXIST = "Museum not found";
  public static final String ERROR_OF_DELETE = "error of delete";
  public static final String ERROR_OF_UPLOAD_IMAGE = "error of upload";
  public static final String USER_NOT_FOUND = "user not found";
  public static final String OBJECT_NOT_FOUND = "not found";
  public static final String OWNER_NOT_FOUND = "owner not found";
  public static final String MUSEUM_NOT_ACTIVATED = "museum not activated";
  public static final String MUSEUM_CANT_BE_DELETED = "museum cannot be removed";
  public static final String WRONG_DATA = "wrong data";

  private static final Map<String, String> ERRORS = Map.ofEntries(
    Map.entry(KEY_NOT_UNIQUE, "Повторяющиеся данные"),
    Map.entry(MUSEUM_NOT_EXIST, "Музей не найден"),
    Map.entry(DESCRIPTION_MOST_BE_SET, "Укажите описание"),
    Map.entry(DATE_MOST_BE_SET, "date must be set"),
    Map.entry(OWNER_NOT_FOUND, "К музею не привязан владелец"),
    Map.entry(MUSEUM_NOT_ACTIVATED, "Музей еще не активирован"),
    Map.entry(MUSEUM_CANT_BE_DELETED, "Музей не может быть удален"),
    Map.entry(WRONG_DATA, "Неверные данные"),
    Map.entry(ERROR_OF_DELETE, "Ошибка удаления"),
    Map.entry(OBJECT_NOT_FOUND, "Объект не найден"),
    Map.entry(ERROR_OF_UPLOAD_IMAGE, "Фото не удалось загрузить"),
    Map.entry(EXHIBITION_NOT_EXIST, "Несуществующая выставка"),
    Map.entry(NAME_MOST_BE_SET, "Укажите название"),
    Map.entry(USER_NOT_FOUND, "Пользователь не найден"),
    Map.entry(ADDRESS_MOST_BE_SET, "Укажите адрес"),
    Map.entry(ILLEGAL_ARGUMENT, "Проверьте поля"),
    Map.entry(NOT_READABLE, "Невозможно преобразовать данные"),
    Map.entry(INVALID_ROLE_OF_USER, "Пользователь с введёнными данными уже существует"));

  public static String getMessageByCode(String code) {
    return ERRORS.get(code);
  }
}
