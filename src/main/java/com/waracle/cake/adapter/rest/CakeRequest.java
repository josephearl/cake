package com.waracle.cake.adapter.rest;

import static com.waracle.cake.adapter.rest.ExampleCakes.*;

import com.waracle.cake.CakeDetails;
import com.waracle.cake.Url;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record CakeRequest(
    @NotNull @Schema(example = CARROT_CAKE_TITLE) String title,
    @NotNull @Schema(example = CARROT_CAKE_DESCRIPTION) String description,
    @NotNull @Pattern(regexp = Url.REGEX) @Schema(example = CARROT_CAKE_IMAGE_URL) String image) {
  public CakeDetails toCakeDetails() {
    return new CakeDetails(title, description, Url.parse(image));
  }
}
