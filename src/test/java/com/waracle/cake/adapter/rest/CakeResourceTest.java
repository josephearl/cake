package com.waracle.cake.adapter.rest;

import static com.waracle.cake.JSONMatcher.equalToJSON;
import static com.waracle.cake.TestCakes.*;
import static com.waracle.cake.TestResources.readResource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.endsWith;

import com.waracle.cake.CakeInventory;
import com.waracle.cake.adapter.db.InMemoryCakeRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@QuarkusTest
@TestHTTPEndpoint(CakeResource.class)
class CakeResourceTest {
  @Inject CakeInventory inventory;

  @BeforeEach
  void setup() {
    QuarkusMock.installMockForInstance(new InMemoryCakeRepository(), inventory);
  }

  @Test
  void readCakesShouldReturnOkWithAllInventoriedCakes() {
    inventory.modifyCake(lemonCake(1L));
    inventory.modifyCake(chocolateCake(2L));

    get()
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body(equalToJSON(readResource("all-cakes.response.json")));
  }

  @Test
  void createCakeShouldReturnOkWithNewlyInventoriedCake() {
    var id = 1L;
    inventory.modifyCake(lemonCake(id));

    given()
        .contentType(ContentType.JSON)
        .body(readResource("lemon-cake.request.json"))
        .when()
        .post()
        .then()
        .statusCode(201)
        .contentType(MediaType.APPLICATION_JSON)
        .header("Location", endsWith("/cakes/" + id))
        .body(equalToJSON(readResource("lemon-cake.response.json")));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "cake-with-null-title.request.json",
        "cake-with-null-description.request.json",
        "cake-with-null-image.request.json",
        "cake-with-invalid-image.request.json"
      })
  void createCakeShouldReturnBadRequestWhenRequiredFieldIsNullOrFieldIsInvalid(
      String resourceName) {
    given()
        .contentType(ContentType.JSON)
        .body(readResource(resourceName))
        .when()
        .post()
        .then()
        .statusCode(400);
  }

  @Test
  void deleteCakesShouldReturnOk() {
    delete().then().statusCode(200);
  }

  @Test
  void readCakeShouldReturnOkWithInventoriedCakeWhenCakeExists() {
    var id = 1L;
    inventory.modifyCake(lemonCake(id));

    get("/{id}", id)
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body(equalToJSON(readResource("lemon-cake.response.json")));
  }

  @Test
  void readCakeShouldReturnNotFoundWhenCakeDoesNotExist() {
    get("/{id}", 1L).then().statusCode(404);
  }

  @Test
  void updateCakeShouldReturnOkWithInventoriedCakeWhenCakeExists() {
    var id = 1L;
    inventory.modifyCake(chocolateCake(id));

    given()
        .contentType(ContentType.JSON)
        .body(readResource("chocolate-cake.request.json"))
        .when()
        .put("/{id}", id)
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body(equalToJSON(readResource("chocolate-cake.response.json")));
  }

  @Test
  void updateCakeShouldReturnNotFoundWhenCakeDoesNotExist() {
    given()
        .contentType(ContentType.JSON)
        .body(readResource("chocolate-cake.request.json"))
        .when()
        .put("/{id}", 1L)
        .then()
        .statusCode(404);
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "cake-with-null-title.request.json",
        "cake-with-null-description.request.json",
        "cake-with-null-image.request.json",
        "cake-with-invalid-image.request.json"
      })
  void updateCakeShouldReturnBadRequestWhenRequiredFieldIsNullOrFieldIsInvalid(
      String resourceName) {
    given()
        .contentType(ContentType.JSON)
        .body(readResource(resourceName))
        .when()
        .put("/{id}", 1)
        .then()
        .statusCode(400);
  }

  @Test
  void deleteCakeShouldReturnOkWhenCakeExists() {
    var id = 1L;
    inventory.modifyCake(lemonCake(id));

    delete("/{id}", id).then().statusCode(200);
  }

  @Test
  void deleteCakeShouldReturnNotFoundWhenCakeDoesNotExist() {
    delete("/{id}", 1L).then().statusCode(404);
  }
}
