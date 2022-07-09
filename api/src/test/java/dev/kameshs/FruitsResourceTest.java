package dev.kameshs;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Map;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FruitsResourceTest {

  @Test
  @Order(1)
  public void testReadiness() {
    given()
        .when()
        .get("/q/health/ready")
        .then()
        .statusCode(200)
        .body("status", is("UP"));
  }

  @Test
  @Order(2)
  public void testDefaultFruit() {
    given()
        .when()
        .get("/api/fruits/default")
        .then()
        .statusCode(200)
        .body("name", is("Apple"));
  }

  @Test
  @Order(3)
  public void testListFruits() {
    ValidatableResponse response = given()
        .when()
        .get("/api/fruits")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200);

    ArrayList<Map<String, ?>> jsonAsArrayList = response.extract().jsonPath().get();

    assertEquals(9, jsonAsArrayList.size());
    assertEquals("Mango", jsonAsArrayList.get(0).get("name"));
    assertEquals("Spring", jsonAsArrayList.get(0).get("season"));
  }

  @Test
  @Order(4)
  public void testAddFruit() {
    JsonObject fruit = new JsonObject()
        .put("name", "Banana")
        .put("season", "All");
    given()
        .header("Content-type", "application/json")
        .and()
        .body(fruit.encode())
        .when()
        .post("/api/fruits/add")
        .then()
        .statusCode(202);
  }

  @Test
  @Order(5)
  public void testDeleteFruit() {
    given()
        .when()
        .delete("/api/fruits/10")
        .then()
        .statusCode(204);
  }

  @Test
  @Order(6)
  public void testFruitsBySeason() {
    ValidatableResponse response = given()
        .when()
        .get("/api/fruits/Winter")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(200);

    ArrayList<Map<String, ?>> jsonAsArrayList = response.extract().jsonPath().get();

    assertEquals(2, jsonAsArrayList.size());
    assertEquals("Orange", jsonAsArrayList.get(0).get("name"));
    assertEquals("Lemon", jsonAsArrayList.get(1).get("name"));
  }

  @Test
  @Order(7)
  public void testDeleteNonExistFruit() {
    given()
        .when()
        .delete("/api/fruits/10")
        .then()
        .statusCode(404);
  }
}