public class UpdateUser {

    private String name;
    private String email;
    private String password;

    public UpdateUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UpdateUser() {
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
