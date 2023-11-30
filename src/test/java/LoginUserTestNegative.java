import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;


@RunWith(Parameterized.class)
public class LoginUserTestNegative {

        private static final String PATH = "api/auth/login";
        private UserApi userApi = new UserApi();
        private String email;
        private String password;
        private LoginUser invalidUser;

        public LoginUserTestNegative(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Parameterized.Parameters
        public static Object[][] getTestData() {
            return new Object[][] {
                    {null, "12345"},
                    {"mafioso6495@mail.ru", null},
                    {"mafioso6495@mail.ru", "jhf348tys"},
                    {"47chcwshcdi", "12345"}
            };
        }

        @Before
        public void setUp() {
            RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
            invalidUser = new LoginUser(email, password);
        }

        @Test
        @DisplayName("User login with incorrect data")
        @Description("Negative test for login user, required fields are empty or non-existing login/password")
        public void loginUserWithNegativeTestData(){
            Response response = userApi.getResponseByPost(invalidUser, PATH);
            response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED)
                    .and()
                    .body("success", equalTo(false))
                    .body("message", equalTo("email or password are incorrect"));
        }
}
