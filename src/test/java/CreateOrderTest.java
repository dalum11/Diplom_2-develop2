import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateOrderTest {


    private static final String PATH = "api/orders";
    private OrderApi orderApi = new OrderApi();
    private UserApi userApi = new UserApi();

    @Before
    public void setBaseURL() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    @Test
    public void createOrderWithAuthorization(){
        LoginUser user = new LoginUser("amtf@gmail.com", "123456");
        LoginUserResponse loginResponse = userApi.getResponseByPost(user, "api/auth/login").as(LoginUserResponse.class);
        Ingredients validIngredients = new Ingredients(new String[] {"61c0c5a71d1f82001bdaaa72", "61c0c5a71d1f82001bdaaa6f"});
        Response createOrderResponse = orderApi.getResponseByPostWithAuthorization(validIngredients, loginResponse.accessToken(), PATH);
        System.out.println(createOrderResponse.getBody().asString());
        createOrderResponse.then().assertThat().statusCode(HttpStatus.SC_OK);
        CreateOrderResponse createOrderResponseAsClass = createOrderResponse.as(CreateOrderResponse.class);
        assertThat(createOrderResponseAsClass.name() , notNullValue(String.class));
        assertThat(createOrderResponseAsClass.success(), equalTo(true));
        assertThat(createOrderResponseAsClass.order().number(), notNullValue(Integer.class));
    }

    @Test
    public void createOrderWithoutAuthorization(){
        Ingredients validIngredients = new Ingredients(new String[] {"61c0c5a71d1f82001bdaaa6c"});
        Response createOrderWithvalidDataResponse = orderApi.getResponseByPostWithoutAuthorization(validIngredients, PATH);
        createOrderWithvalidDataResponse.then().assertThat().statusCode(HttpStatus.SC_OK);
        CreateOrderResponse createOrderWithValidDataResponseAsClass = createOrderWithvalidDataResponse.as(CreateOrderResponse.class);
        assertThat(createOrderWithValidDataResponseAsClass.name() , notNullValue(String.class));
        assertThat(createOrderWithValidDataResponseAsClass.success(), equalTo(true));
        assertThat(createOrderWithValidDataResponseAsClass.order().number(), notNullValue(Integer.class));
    }

    @Test
    public void createOrderWithoutIngredients(){
        Ingredients emptyIngredients = new Ingredients(new String[]{});
        Response response = orderApi.getResponseByPostWithoutAuthorization(emptyIngredients, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    public void createOrderWithInvalidIngredients(){
        Ingredients ingredients = new Ingredients(new String[] {"61c0c5a71++f82001bdaaa6c", "61c0c5a71d1f8==01bdaaa6c"});
        Response response = orderApi.getResponseByPostWithoutAuthorization(ingredients, PATH);
        response.then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
