// TODO : Data Transfer Object
package com.revature.richbank.web.dto;

public class LoginCreds {

    private String login_id;
    private String login_password;


    // TODO: Jackson requires a no arg constructor

    /*
    public LoginCreds(String login_id) {
        this.login_id = login_id;
    }
    */

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
}
