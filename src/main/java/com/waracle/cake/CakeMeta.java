package com.waracle.cake;

public sealed interface CakeMeta permits Cake, CakeDetails {
  String title();

  String description();

  Url image();
}
