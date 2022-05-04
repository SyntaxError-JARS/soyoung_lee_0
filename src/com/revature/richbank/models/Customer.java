package com.revature.richbank.models;

public class Customer {
    private String customer_name ;
    private String email_1       ;
    private String phone_1       ;
    private String address       ;
    private String login_id      ;
    private String login_password;
    private String login_date    ;
    private boolean block    ;

    public Customer(String customer_name, String email_1, String phone_1, String address, String login_id, String login_password, String login_date, boolean block) {
        super();
        this.customer_name = customer_name;
        this.email_1 = email_1;
        this.phone_1 = phone_1;
        this.address = address;
        this.login_id = login_id;
        this.login_password = login_password;
        this.login_date = login_date;
        this.block = block;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail_1() {
        return email_1;
    }

    public void setEmail_1(String email_1) {
        this.email_1 = email_1;
    }

    public String getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(String phone_1) {
        this.phone_1 = phone_1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getLogin_date() {
        return login_date;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_name='" + customer_name + '\'' +
                ", email_1='" + email_1 + '\'' +
                ", phone_1='" + phone_1 + '\'' +
                ", address='" + address + '\'' +
                ", login_id='" + login_id + '\'' +
                ", login_password='" + login_password + '\'' +
                ", login_date='" + login_date + '\'' +
                ", block=" + block +
                '}';
    }
}
