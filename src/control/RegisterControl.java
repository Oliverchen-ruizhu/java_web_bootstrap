package control;

import java.io.Serializable;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: java Bean
 * @Date:Create：in 2020/10/12 16:08
 * @Modified By：
 */
public class RegisterControl implements Serializable {
    private String username;//用户姓名
    private String userType;//用户类型
    private String userAccount;//用户账号
    private String pwd1;//用户密码
    private String phone_number;//用户电话
    private String email_address;//用户电子邮件
    private String working_address;//用户工作地址

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setWorking_address(String working_address) {
        this.working_address = working_address;
    }

    public String getWorking_address() {
        return working_address;
    }

    public RegisterControl(){
    }

}
