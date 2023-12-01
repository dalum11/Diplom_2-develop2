import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    public Response getResponseByPost(CreateUser user, String path) {
        return  given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(path);
    }
    public Response getResponseByPost(LoginUser user, String path) {
        return  given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(path);
    }

    public Response getResponseByDelete(LoginUser user, String token, String path){
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .and()
                .body(user)
                .when()
                .delete(path);
    }

    public Response getResponseByPathWithAuthorization(UpdateUser user, String token, String path) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .and()
                .body(user)
                .when()
                .patch(path);
    }

    public Response getResponseByPathWithoutAuthorization(UpdateUser user, String path) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .patch(path);
    }
}
