package com.revature.richbank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {


    // Encapsulated these variables and attributes.
    // These attributes suppose to exactly match with the verbatim for JSON
@JsonIgnore
    private int customer_id   ;
    private String customer_name ;
    private String email_1       ;
    private String phone_1       ;
    private String address       ;
    private String login_id      ;

    private String login_password;
    //private String login_date    ;
    private boolean block    ;

    /*
     * Constructor is another pillar of Polymorphism.
     *  --- Method overloading
     *          same method signature ( same return type, same name )
     *          parameter change
     */

    //public Customer(String customer_name, String email_1, String phone_1, String address, String login_id, String login_password, String login_date, boolean block) {


    public Customer(int customer_id, String customer_name, String email_1, String phone_1, String address, String login_id, String login_password, boolean block) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.email_1 = email_1;
        this.phone_1 = phone_1;
        this.address = address;
        this.login_id = login_id;
        this.login_password = login_password;
        this.block = block;
    }

    public Customer() {
        super();
    }

    public Customer(String login_id, String login_password) {
        this.login_id = login_id;
        this.login_password = login_password;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", email_1='" + email_1 + '\'' +
                ", phone_1='" + phone_1 + '\'' +
                ", address='" + address + '\'' +
                ", login_id='" + login_id + '\'' +
                ", login_password='" + login_password + '\'' +
                ", block=" + block +
                '}';
    }
}
