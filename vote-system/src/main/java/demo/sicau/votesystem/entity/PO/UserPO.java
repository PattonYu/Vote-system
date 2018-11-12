package demo.sicau.votesystem.entity.PO;

/**
 * @Author PattonYu
 * @Date 9:34 2018/10/28
 * @Description:
 */
public class UserPO {
    /**
     * user表的主键id
     * */
    private String id;

    private String username;

    private String password;

    private String realName;

    private String email;

    public UserPO(String id, String password, String realName, String email){
        this.email = email;
        this.id = id;
        this.realName = realName;
        this.password = password;
    }

    public UserPO(String id, String username, String password, String realName, String email) {
        this.email = email;
        this.id = id;
        this.realName = realName;
        this.password = password;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return username.concat(password).concat(email).concat(realName).concat(id);
    }
}
