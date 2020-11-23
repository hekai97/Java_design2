package hk.model;
/**
 * UP--UserPassword
 * 该类用来定义一个用户类
 * 用户的属性包含用户名和密码
 * */
public class UP {
    private String user;
    private String password;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
