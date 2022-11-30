package com.waracle.cake;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UrlTest {
  @ParameterizedTest
  @ValueSource(
      strings = {"http://example.com", "https://example.com", "http://example.com/image.png"})
  void parseShouldReturnUrl(String text) {
    var url = Url.parse(text);

    assertThat(url.toString()).isEqualTo(text);
  }

  @Test
  void parseShouldThrowExceptionWhenTextIsNull() {
    assertThatThrownBy(() -> Url.parse(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("text");
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "invalid", "ftp://invalid/image.jpg", "http://", "../image.jpg"})
  void parseShouldThrowExceptionWhenTextIsNotValidUrl(String text) {
    assertThatThrownBy(() -> Url.parse(text))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("text");
  }
}
