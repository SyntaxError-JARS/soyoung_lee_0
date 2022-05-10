package com.revature.richbank.dos;

import com.revature.richbank.models.Account;
import com.revature.richbank.models.Customer;
import com.revature.richbank.models.Trans;
import com.revature.richbank.models.Trans;
import com.revature.richbank.util.ConnectionFactory;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;

public class TransDao implements Crudable<Trans> {

    private final Logger logger = Logger.getLogger(true);

    @Override
    public Trans create(Trans newTrans) {
        System.out.println("TransDao::create() : creating new trans");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into trans values ("

            String sql = "insert into trans ( trans_id, account_number, trans_date, trans_type, amount)" +
                          "values (default, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newTrans.getAccount_number());
            ps.setString(2, newTrans.getTrans_date());
            ps.setString(3, newTrans.getTrans_type());
            ps.setDouble(4, newTrans.getAmount());

            int checkInsert = ps.executeUpdate();
            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }

        return newTrans;

    }

    @Override
    public Trans[] findAll() throws IOException {
        return null;
    }


    public Trans[] findAll(String account_number) throws IOException {
        logger.info("TransDao::findAll() : finding all trans");

        // FileWriter's evil counterpart, to read files

        Trans[] transs = new Trans[10];
        int index = 0;
        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from trans where account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account_number);

            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query


            while (rs.next()) { // the last line of the file is null
                Trans trans = new Trans();

                // column lable must match table column name
                trans.setTrans_id(rs.getInt("trans_id"));
                trans.setAccount_number(rs.getString("account_number"));
                trans.setTrans_date(rs.getString("trans_date"));
                trans.setTrans_date(rs.getString("trans_type"));
                trans.setAmount(rs.getDouble("amount"));

                logger.info("Going to the next line for our following index.");
                transs[index] = trans;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("Returning trans information to user.");
        return transs;
    }

    @Override
    public Trans findById(String id) {

        logger.info("TransDao::findById() : find a transaction by trans_id ");


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from trans_info where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            Trans trans = new Trans();

            trans.setAccount_id(    rs.getInt(   "account_id"  ));
            trans.setAccount_number(rs.getString("account_number"       ));
            trans.setLast_date(     rs.getString("account_type"      ));
            trans.setAccount_type(  rs.getString("first_date"       ));
            trans.setFirst_date(    rs.getString("last_date"       ));
            trans.setInterest(      rs.getDouble("interest"));
            trans.setTotal(         rs.getDouble("total"));
            trans.setCustomer_id_1( rs.getInt("customer_id_1"));
            trans.setCustomer_id_2( rs.getInt("customer_id_2"));


            return account;

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean update(Trans updateTrans) {
        logger.info("AccountDao::update() : update an trans" + updateTrans);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update trans set trans_date = ?, trans_type = ?, amount = ? where trans_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updateTrans.getTrans_date());
            ps.setString(2, updateTrans.getTrans_type());
            ps.setDouble(3, updateTrans.getAmount());
            ps.setInt(4, updateTrans.getTrans_id());

            int checkUpdate = ps.executeUpdate();
            if (checkUpdate == 0) {
                throw new RuntimeException();
            } else return true;

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}