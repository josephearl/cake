package com.waracle.cake;

import static com.waracle.cake.TestResources.readResource;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

@QuarkusIntegrationTest
class CakeIntegrationTest {
  @Test
  void cakeApisShouldOperateConsistently() {
    // We should start with 0 cakes
    get("/cakes")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body(".", hasSize(0));

    // Then we add a cake
    var cakeLocation =
        given()
            .contentType(ContentType.JSON)
            .body(readResource("lemon-cake.request.json"))
            .when()
            .post("/cakes")
            .then()
            .statusCode(201)
            .contentType(MediaType.APPLICATION_JSON)
            .body("title", equalTo("Lemon Cake"))
            .extract()
            .header("Location");

    // Now there should be 1 cake
    get("/cakes")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body(".", hasSize(1));

    // And we should be able to get that specific cake
    get(cakeLocation)
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", equalTo("Lemon Cake"));

    // And update it
    given()
        .contentType(ContentType.JSON)
        .body(readResource("chocolate-cake.request.json"))
        .when()
        .put(cakeLocation)
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", equalTo("Chocolate Cake"))
        .extract();

    // And then delete it
    delete(cakeLocation).then().statusCode(200);

    // And we should be back to 0 cakes
    get("/cakes")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body(".", hasSize(0));
  }
}
