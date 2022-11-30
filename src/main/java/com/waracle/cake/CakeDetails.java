package com.waracle.cake;

import lombok.NonNull;

public record CakeDetails(@NonNull String title, @NonNull String description, @NonNull Url image)
    implements CakeMeta {}
