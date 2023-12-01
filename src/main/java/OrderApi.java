import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    public Response getResponseByGetWithAuthorization(String token, String path) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .when()
                .get(path);
    }

    public Response getResponseByGetWithoutAuthorization(String path) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(path);
    }

    public Response getResponseByPostWithAuthorization(Ingredients ingredients, String token, String path) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .and()
                .body(ingredients)
                .when()
                .post(path);
    }

    public Response getResponseByPostWithoutAuthorization(Ingredients ingredients, String path) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(ingredients)
                .when()
                .post(path);
    }
}
