import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {

    private static final String PATH = "api/auth/register";
    private static CreateUser user = new CreateUser("mafioso6495@mail.ru", "12345", "jiorno jovanna");
    private static UserApi userApi = new UserApi();

    @Before
    public void setBaseURL() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void createUserReturn201(){
        Response response = userApi.getResponseByPost(user, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_OK)
                .and()
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    public void createUserTwiceReturn403() {
        CreateUser user = new CreateUser("amtf@gmail.com", "12345", "ATF");
        userApi.getResponseByPost(user, PATH);
        Response response = userApi.getResponseByPost(user, PATH);
        response.then().assertThat().body("success", equalTo(false))
                .body("message", equalTo("User already exists"))
                .and()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void createUserWithoutEmailReturn403() {
        CreateUser userWithoutEmail = new CreateUser(null, "12345", "Itachi");
        Response response = userApi.getResponseByPost(userWithoutEmail, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    public void createUserWithoutPasswordReturn403() {
        CreateUser userWithoutEmail = new CreateUser("uchiha_itachi@gmail.com", null, "Itachi");
        Response response = userApi.getResponseByPost(userWithoutEmail, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    public void createUserWithoutNameReturn403() {
        CreateUser userWithoutEmail = new CreateUser("uchiha_itachi@gmail.com", "1234", null);
        Response response = userApi.getResponseByPost(userWithoutEmail, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @AfterClass
    public static void deleteTestData(){
        LoginUser loginUser = new LoginUser(user.email(), user.password());
        LoginUserResponse loginUserResponse = userApi.getResponseByPost(loginUser, "api/auth/login").as(LoginUserResponse.class);
        Response response = userApi.getResponseByDelete(loginUser, loginUserResponse.accessToken(),"api/auth/user");
        System.out.println(response.statusCode());
    }
}
