import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetUserOrdersTest {


    private OrderApi orderApi = new OrderApi();
    private UserApi userApi = new UserApi();
    private static final String PATH = "api/orders";

    @Before
    public void setBaseURL() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void getUserOrdersWithAuthorization(){
        LoginUser user = new LoginUser("amtf@gmail.com", "123456");
        LoginUserResponse loginResponse = userApi.getResponseByPost(user, "api/auth/login").as(LoginUserResponse.class);
        Response getOrdersResponse = orderApi.getResponseByGetWithAuthorization(loginResponse.accessToken(), PATH);
        Orders orders = getOrdersResponse.as(Orders.class);
        assertThat(orders.orders(), notNullValue());
        assertThat(orders.success(), equalTo(true));
        assertThat(orders.total(), notNullValue(Integer.class));
        assertThat(orders.totalToday(), notNullValue(Integer.class));
    }

    @Test
    public void getUserOrdersWithoutAuthorization(){
        Response getOrderResponse = orderApi.getResponseByGetWithoutAuthorization(PATH);
        getOrderResponse.then().assertThat().body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"))
                .and()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
