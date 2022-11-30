package com.waracle.cake.adapter.rest;

import static com.waracle.cake.adapter.rest.ExampleCakes.*;

import com.waracle.cake.Cake;
import lombok.NonNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record CakeResponse(
    @Schema(example = "1") long id,
    @Schema(example = CARROT_CAKE_TITLE) String title,
    @Schema(example = CARROT_CAKE_DESCRIPTION) String description,
    @Schema(example = CARROT_CAKE_IMAGE_URL) String image) {
  public static CakeResponse from(@NonNull Cake cake) {
    return new CakeResponse(cake.id(), cake.title(), cake.description(), cake.image().toString());
  }
}
