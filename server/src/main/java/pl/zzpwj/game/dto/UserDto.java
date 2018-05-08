package pl.zzpwj.game.dto;

public class UserDto {
    private String login;
    private String email;
    private String password;

    public UserDto(String login, String email) {
        this.login = login;
        this.email = email;
        this.password = "";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
