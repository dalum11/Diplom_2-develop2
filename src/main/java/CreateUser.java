public class CreateUser {

    private String email;
    private String password;
    private String name;

    public CreateUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUser(){}

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public String name() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
