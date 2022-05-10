package com.revature.richbank.dos;

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
        logger.info("TransDao::findAll() : finding all trans");

        // FileWriter's evil counterpart, to read files

        Trans[] transs = new Trans[10];
        int index = 0;
        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from trans";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

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
        return null;
    }

    @Override
    public boolean update(Trans updateTrans) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}