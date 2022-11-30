package com.waracle.cake.adapter.rest;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.waracle.cake.Url;
import org.junit.jupiter.api.Test;

class CakeRequestTest {
  @Test
  void toCakeShouldReturnCakeCopiedFromRequest() {
    var request = new CakeRequest(LEMON_CAKE_TITLE, LEMON_CAKE_DESCRIPTION, LEMON_CAKE_IMAGE_URL);

    var details = request.toCakeDetails();

    assertThat(details.title()).isEqualTo(request.title());
    assertThat(details.description()).isEqualTo(request.description());
    assertThat(details.image()).isEqualTo(Url.parse(request.image()));
  }
}
