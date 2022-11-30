package com.waracle.cake;

import lombok.NonNull;

public record Cake(long id, @NonNull String title, @NonNull String description, @NonNull Url image)
    implements CakeMeta {
  public static Cake from(long id, CakeDetails details) {
    return new Cake(id, details.title(), details.description(), details.image());
  }

  public Cake withDetails(@NonNull CakeDetails details) {
    return from(id, details);
  }
}
