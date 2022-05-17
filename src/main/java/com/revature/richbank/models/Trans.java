package com.revature.richbank.models;

public class Trans {
    private int trans_id;
    private String account_number;
    private String trans_type;
    private String trans_date;

    private String from_account;

    private String to_account;
    private double amount;




    public Trans() {
        super();
    }

    public Trans(int trans_id, String account_number, String trans_type, String trans_date, String from_account, String to_account, double amount) {
        this.trans_id = trans_id;
        this.account_number = account_number;
        this.trans_type = trans_type;
        this.trans_date = trans_date;
        this.from_account = from_account;
        this.to_account = to_account;
        this.amount = amount;
    }

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public String getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(String trans_date) {
        this.trans_date = trans_date;
    }

    public String getFrom_account() {
        return from_account;
    }

    public void setFrom_account(String from_account) {
        this.from_account = from_account;
    }

    public String getTo_account() {
        return to_account;
    }

    public void setTo_account(String to_account) {
        this.to_account = to_account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Trans{" +
                "trans_id=" + trans_id +
                ", account_number='" + account_number + '\'' +
                ", trans_type='" + trans_type + '\'' +
                ", trans_date='" + trans_date + '\'' +
                ", from_account='" + from_account + '\'' +
                ", to_account='" + to_account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
