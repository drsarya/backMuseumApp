package src.model;

public enum Role {


  ADMIN(1),
  USER(2),
  MUSEUM(3);

  private final int id;

  Role(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static Role fromId(Integer id) {
    if (id == null) {
      return null;
    }

    for (var value : values()) {
      if (id.equals(value.id)) {
        return value;
      }
    }

    return null;

  }
}
