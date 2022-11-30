package com.waracle.cake.adapter.rest;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CakeResponseTest {
  @Test
  void fromShouldReturnResponseCopiedFromCake() {
    var cake = lemonCake(1L);

    var response = CakeResponse.from(cake);

    assertThat(response.id()).isEqualTo(cake.id());
    assertThat(response.title()).isEqualTo(cake.title());
    assertThat(response.description()).isEqualTo(cake.description());
    assertThat(response.image()).isEqualTo(cake.image().toString());
  }

  @Test
  void fromShouldThrowExceptionWhenCakeIsNull() {
    assertThatThrownBy(() -> CakeResponse.from(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("cake");
  }
}
