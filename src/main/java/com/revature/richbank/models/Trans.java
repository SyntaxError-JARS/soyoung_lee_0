package com.revature.richbank.models;

public class Trans {
    private int trans_id;
    private String account_number;
    private String trans_date;
    private String trans_type;
    private int amount;

    public Trans(int trans_id, String account_number, String trans_date, String trans_type, int amount) {
        this.trans_id = trans_id;
        this.account_number = account_number;
        this.trans_date = trans_date;
        this.trans_type = trans_type;
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

    public String getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(String trans_date) {
        this.trans_date = trans_date;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public void setTrans_type(String trans_type) {
        this.trans_type = trans_type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Trans{" +
                "trans_id=" + trans_id +
                ", account_number='" + account_number + '\'' +
                ", trans_date='" + trans_date + '\'' +
                ", trans_type='" + trans_type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
