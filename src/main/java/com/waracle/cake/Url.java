package com.waracle.cake;

import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class Url {
  public static final String REGEX = "^https?:\\/\\/[^.]+\\.[^.]+.*$";
  private static final Pattern PATTERN = Pattern.compile(REGEX);

  @NonNull private final String text;

  private Url(@NonNull String text) {
    if (!PATTERN.matcher(text).matches())
      throw new IllegalArgumentException("text must be a valid URL but is " + text);
    this.text = text;
  }

  public static Url parse(@NonNull String text) {
    return new Url(text);
  }

  @Override
  public String toString() {
    return text;
  }
}
