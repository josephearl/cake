package com.waracle.cake;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestResources {
  @SneakyThrows
  public static String readResource(String name) {
    var resource = TestResources.class.getResource("/" + name);
    if (resource == null) throw new FileNotFoundException(name + " not found");
    return Files.readString(Path.of(resource.getFile()));
  }
}
