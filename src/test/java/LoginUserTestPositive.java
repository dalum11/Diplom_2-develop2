import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginUserPositive {

    private static final String PATH = "api/auth/login";
    private UserApi userApi = new UserApi();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    @DisplayName("User login with correct data")
    @Description("Basic test for login user, contains existing data, positive")
    public void loginUserWithExistingData() {
        LoginUser validUser = new LoginUser("amtf@gmail.com", "123456");
        Response response = userApi.getResponseByPost(validUser, PATH);
        LoginUserResponse loginUserResponse = response.as(LoginUserResponse.class);
        System.out.println(response.getBody().asString());
        response.then().assertThat().statusCode(HttpStatus.SC_OK)
                .and()
                .body("success", equalTo(true))
                .body("accessToken", notNullValue(String.class))
                .body("refreshToken", notNullValue(String.class));
        assertThat(loginUserResponse.user().email(), equalTo("amtf@gmail.com"));
        assertThat(loginUserResponse.user().name(), equalTo("ATF"));
    }
}
