package com.waracle.cake;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestCakes {
  public static final String LEMON_CAKE_TITLE = "Lemon Cake";
  public static final String LEMON_CAKE_DESCRIPTION = "A tangy lemon drizzle cake.";
  public static final String LEMON_CAKE_IMAGE_URL =
      "https://charlotteslivelykitchen.com/wp-content/uploads/2021/04/lemon-drizzle-loaf-cake-8.jpg";
  public static final Url LEMON_CAKE_IMAGE = Url.parse(LEMON_CAKE_IMAGE_URL);

  public static Cake lemonCake(long id) {
    return new Cake(id, LEMON_CAKE_TITLE, LEMON_CAKE_DESCRIPTION, LEMON_CAKE_IMAGE);
  }

  public static CakeDetails lemonCakeDetails() {
    return new CakeDetails(LEMON_CAKE_TITLE, LEMON_CAKE_DESCRIPTION, LEMON_CAKE_IMAGE);
  }

  public static final String CHOCOLATE_CAKE_TITLE = "Chocolate Cake";
  public static final String CHOCOLATE_CAKE_DESCRIPTION = "A rich chocolatey cake.";
  public static final String CHOCOLATE_CAKE_IMAGE_URL =
      "https://charlotteslivelykitchen.com/wp-content/uploads/2019/01/chocolate-cake-3.jpg";
  public static final Url CHOCOLATE_CAKE_IMAGE = Url.parse(CHOCOLATE_CAKE_IMAGE_URL);

  public static Cake chocolateCake(long id) {
    return new Cake(id, CHOCOLATE_CAKE_TITLE, CHOCOLATE_CAKE_DESCRIPTION, CHOCOLATE_CAKE_IMAGE);
  }

  public static CakeDetails chocolateCakeDetails() {
    return new CakeDetails(CHOCOLATE_CAKE_TITLE, CHOCOLATE_CAKE_DESCRIPTION, CHOCOLATE_CAKE_IMAGE);
  }
}
