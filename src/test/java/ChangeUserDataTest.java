import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ChangeUserDataTest {

    private static final String PATH = "api/auth/user";
    private static UserApi userApi = new UserApi();

    @Before
    public void setBaseURL() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void checkChangeUserDataWithAuthorization(){
        LoginUser loginUser = new LoginUser("amtf@gmail.com", "123456");
        String accessToken = userApi.getResponseByPost(loginUser, "api/auth/login").as(LoginUserResponse.class).accessToken();
        UpdateUser user = new UpdateUser("nikolay", "oleg.makarow1997@gmail.com", "123456");
        UpdateUserResponse response = userApi.getResponseByPathWithAuthorization(user, accessToken, PATH).as(UpdateUserResponse.class);
        assertThat(response.success(), equalTo("true"));
        assertThat(response.user().name(), equalTo("nikolay"));
        assertThat(response.user().email(), equalTo("oleg.makarow1997@gmail.com"));
    }

    @Test
    public void checkChangeUserDataWithoutAuthorization(){
        UpdateUser user = new UpdateUser("nikolay", "oleg.makarow1997@gmail.com", "123456");
        Response response = userApi.getResponseByPathWithoutAuthorization(user, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @AfterClass
    public static void flushChangedData(){
        LoginUser loginUser = new LoginUser("oleg.makarow1997@gmail.com", "123456");
        String accessToken = userApi.getResponseByPost(loginUser, "api/auth/login").as(LoginUserResponse.class).accessToken();
        UpdateUser user = new UpdateUser("ATF", "amtf@gmail.com", "123456");
        Response response = userApi.getResponseByPathWithAuthorization(user, accessToken, PATH);
        System.out.println(response.statusCode());
    }
}
