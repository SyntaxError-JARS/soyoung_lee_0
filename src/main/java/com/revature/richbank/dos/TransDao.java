package com.revature.richbank.dos;

import com.revature.richbank.models.Trans;
import com.revature.richbank.util.ConnectionFactory;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TransDao implements Crudable<Trans> {

    private final Logger logger = Logger.getLogger(true);

    @Override
    public Trans create(Trans newTrans) {
        System.out.println("TransDao::create() : creating new trans");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into trans values ("

            String sql = "insert into trans ( trans_id, account_number, trans_date, trans_type, from_account, to_account, amount)" +
                          "values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newTrans.getAccount_number());
            ps.setString(2, newTrans.getTrans_date());
            ps.setString(3, newTrans.getTrans_type());
            ps.setString(4, newTrans.getFrom_account());
            ps.setString(5, newTrans.getTo_account());
            ps.setDouble(6, newTrans.getAmount());

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
    public List<Trans> findAll() throws IOException {
        return null;
    }


    public List<Trans> findAll(String account_number) throws IOException {
        logger.info("TransDao::findAll() : finding all trans");

        // FileWriter's evil counterpart, to read files

        List<Trans> transList = new LinkedList<>();
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
                trans.setTrans_date(rs.getString("trans_type"));
                trans.setTrans_date(rs.getString("trans_date"));
                trans.setFrom_account(rs.getString("from_account"));
                trans.setTo_account(rs.getString("to_account"));
                trans.setAmount(rs.getDouble("amount"));


                logger.info("Going to the next line for our following index.");
                transList.add(trans);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        logger.info("Returning trans information to user.");
        return transList;
    }

    @Override
    public Trans findById(String id) {

        logger.info("TransDao::findById() : find a transaction by trans_id ");


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // TODO : Later I will make a view with name "trans_inf0"
            //        for the detail information for a transaction
            //        with customer table and account table
            //          String sql = "select * from trans where trans_id = ?";

            String sql = "select * from trans where trans_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            Trans trans = new Trans();

            trans.setTrans_id( rs.getInt(   "trans_id"));
            trans.setAccount_number(rs.getString("account_number"));
            trans.setTrans_type(rs.getString("trans_type"));
            trans.setTrans_date(rs.getString("trans_date"));
            trans.setFrom_account(rs.getString("from_account"));
            trans.setTo_account(rs.getString("to_account"));
            trans.setAmount(rs.getDouble("amount"));

            return trans;

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean update(Trans updateTrans) {
        logger.info("AccountDao::update() : update an trans" + updateTrans);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update trans set trans_date = ?, trans_type = ?, from_account = ?, to_account = ?, amount = ? where trans_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updateTrans.getTrans_date());
            ps.setString(2, updateTrans.getTrans_type());
            ps.setString(2, updateTrans.getFrom_account());
            ps.setString(3, updateTrans.getTo_account());
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