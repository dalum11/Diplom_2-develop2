public class UpdateUserResponse {

    private String success;

    public UpdateUserResponse(String success) {
        this.success = success;
    }

    public String success() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
