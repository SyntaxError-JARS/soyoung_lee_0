package com.revature.richbank.dos;

import com.revature.richbank.models.Account;
import com.revature.richbank.util.ConnectionFactory;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountDao implements Crudable<Account> {

    private Logger logger = Logger.getLogger();
    @Override
    public Account create(Account newAccount) {
        System.out.println("AccountDao::create() : creating new account");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account " +
                    "values (account_id, account_number, account_type, first_date, last_date, interest, total, customer_id_1, customer_id_2)" +
                    "       (default,    ?,               ?,           ?,           ?,        default,  ?,     ?,              ?)";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newAccount.getAccount_number());
            ps.setString(2, newAccount.getAccount_type());
            ps.setString(3, newAccount.getFirst_date());
            ps.setDouble(4, newAccount.getInterest());
            ps.setDouble(5, newAccount.getTotal());
            ps.setInt(6, newAccount.getCustomer_id_1());
            ps.setInt(7, newAccount.getCustomer_id_2());;

            int checkInsert = ps.executeUpdate();
            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }

        return newAccount;

    }

    @Override
    public List<Account> findAll() throws IOException {
        logger.info("AccountDao::findAll() : finding all accounts");

        // FileWriter's evil counterpart, to read files

        List<Account> accountList = new LinkedList<>();

        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account_info";
            Statement s = conn.createStatement();

            // generally not used
            // ResultSet rs = conn.createStatement().executeQuery("select * from account")

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                // column lable must match table column name
                account.setAccount_id(    rs.getInt(   "account_id"  ));
                account.setAccount_number(rs.getString("account_number"       ));
                account.setLast_date(     rs.getString("account_type"      ));
                account.setAccount_type(  rs.getString("first_date"       ));
                account.setFirst_date(    rs.getString("last_date"       ));
                account.setInterest(      rs.getDouble("interest"));
                account.setTotal(         rs.getDouble("total"));
                account.setCustomer_id_1( rs.getInt("customer_id_1"));
                account.setCustomer_id_2( rs.getInt("customer_id_2"));

                logger.info("Going to the next line for our following index.");
                accountList.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return accountList;

    }


    public List<Account> findAll(String login_id) throws IOException {
        logger.info("AccountDao::findAll() : finding all accounts with id ");

        // FileWriter's evil counterpart, to read files

        List<Account> accountList = new LinkedList<>();
        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account_info where login_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                // column lable must match table column name
                account.setAccount_id(    rs.getInt(   "account_id"  ));
                account.setAccount_number(rs.getString("account_number"       ));
                account.setLast_date(     rs.getString("account_type"      ));
                account.setAccount_type(  rs.getString("first_date"       ));
                account.setFirst_date(    rs.getString("last_date"       ));
                account.setInterest(      rs.getDouble("interest"));
                account.setTotal(         rs.getDouble("total"));
                account.setCustomer_id_1( rs.getInt("customer_id_1"));
                account.setCustomer_id_2( rs.getInt("customer_id_2"));

                logger.info("Going to the next line for our following index.");
                accountList.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return accountList;
    }

    public List<Account> findAll(String login_id, String login_password) throws IOException {
        logger.info("AccountDao::findAll() : finding all accounts with id and password");

        // FileWriter's evil counterpart, to read files

        List<Account> accountList = new LinkedList<>();
        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account_info where login_id = ? and login_password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id);
            ps.setString(2, login_password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                // column lable must match table column name
                account.setAccount_id(    rs.getInt(   "account_id"  ));
                account.setAccount_number(rs.getString("account_number"       ));
                account.setLast_date(     rs.getString("account_type"      ));
                account.setAccount_type(  rs.getString("first_date"       ));
                account.setFirst_date(    rs.getString("last_date"       ));
                account.setInterest(      rs.getDouble("interest"));
                account.setTotal(         rs.getDouble("total"));
                account.setCustomer_id_1( rs.getInt("customer_id_1"));
                account.setCustomer_id_2( rs.getInt("customer_id_2"));

                logger.info("Going to the next line for our following index.");
                accountList.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return accountList;
    }

    @Override
    public Account findById(String id) {
        logger.info("AccountDao::findById() : find a account by login_id");


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from account_info where login_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            Account account = new Account();

            account.setAccount_id(    rs.getInt(   "account_id"  ));
            account.setAccount_number(rs.getString("account_number"       ));
            account.setLast_date(     rs.getString("account_type"      ));
            account.setAccount_type(  rs.getString("first_date"       ));
            account.setFirst_date(    rs.getString("last_date"       ));
            account.setInterest(      rs.getDouble("interest"));
            account.setTotal(         rs.getDouble("total"));
            account.setCustomer_id_1( rs.getInt("customer_id_1"));
            account.setCustomer_id_2( rs.getInt("customer_id_2"));


            return account;

        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean update(Account updateAccount) {
        logger.info("AccountDao::update() : update an account" + updateAccount);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set account_type = ?, last_date = ?, interest = ?, total = ? where account_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updateAccount.getAccount_type());
            ps.setString(2, updateAccount.getLast_date());
            ps.setDouble(3, updateAccount.getInterest());
            ps.setDouble(4, updateAccount.getTotal());
            ps.setInt(5, updateAccount.getAccount_id());

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
        logger.info("AccountDao::delete() : delete a account by account_id: " + id);
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into customer values ("

            String sql = "delete from account where account_id = ?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, id);

            int checkUpdate = ps.executeUpdate();
            if (checkUpdate == 0) {
                throw new RuntimeException();
            } else return true;

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }
}
