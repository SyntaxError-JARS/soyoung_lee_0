package com.revature.richbank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Account {
    @JsonIgnore
    private int account_id;
    private String account_number;
    private String account_type;
    private String first_date;
    private String last_date;
    private double interest;
    private double total;
    private int customer_id_1;
    private int customer_id_2;

    public Account(){
        super();
    }

    public Account(int account_id, String account_number, String account_type, String first_date, String last_date, double interest, double total, int customer_id_1, int customer_id_2) {
        super();
        this.account_id = account_id;
        this.account_number = account_number;
        this.account_type = account_type;
        this.first_date = first_date;
        this.last_date = last_date;
        this.interest = interest;
        this.total = total;
        this.customer_id_1 = customer_id_1;
        this.customer_id_2 = customer_id_2;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getFirst_date() {
        return first_date;
    }

    public void setFirst_date(String first_date) {
        this.first_date = first_date;
    }

    public String getLast_date() {
        return last_date;
    }

    public void setLast_date(String last_date) {
        this.last_date = last_date;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCustomer_id_1() {
        return customer_id_1;
    }

    public void setCustomer_id_1(int customer_id_1) {
        this.customer_id_1 = customer_id_1;
    }

    public int getCustomer_id_2() {
        return customer_id_2;
    }

    public void setCustomer_id_2(int customer_id_2) {
        this.customer_id_2 = customer_id_2;
    }

    @Override
    public String
    toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", account_number='" + account_number + '\'' +
                ", account_type='" + account_type + '\'' +
                ", first_date='" + first_date + '\'' +
                ", last_date='" + last_date + '\'' +
                ", interest=" + interest +
                ", total=" + total +
                ", customer_id_1='" + customer_id_1 + '\'' +
                ", customer_id_2='" + customer_id_2 + '\'' +
                '}';
    }
}
