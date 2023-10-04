public class LoginUserResponse {

    private boolean success;
    private String accessToken;
    private String refreshToken;
    private User user;

    public LoginUserResponse(boolean success, String accessToken, String refreshToken, User user) {
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public LoginUserResponse() {
    }

    public boolean success() {
        return success;
    }

    public String accessToken() {
        return accessToken;
    }

    public String refreshToken() {
        return refreshToken;
    }

    public User user() {
        return user;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
