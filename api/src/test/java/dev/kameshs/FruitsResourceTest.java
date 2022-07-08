package dev.kameshs;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitsResourceTest {

  @Test
  public void testReadiness() {
    given()
        .when()
        .get("/health/ready/")
        .then()
        .statusCode(200)
        .body("status", is("UP"));
  }

}