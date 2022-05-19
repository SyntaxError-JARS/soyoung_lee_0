package com.revature.richbank.dos;

import com.revature.richbank.exceptions.ResourcePersistenceException;
import com.revature.richbank.models.Account;
import com.revature.richbank.util.ConnectionFactory;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class AccountDao implements Crudable<Account> {

    private Logger logger = Logger.getLogger();
    @Override
    public Account create(Account newAccount) {
        System.out.println("AccountDao::create() : creating new account");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account " +
                    "          (account_id, account_number, account_type, first_date, last_date, interest, total, customer_id_1, customer_id_2)" +
                    "   values (default,    ?,               ?,           ?,           ?,        ?,  ?,     ?,              ?)";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newAccount.getAccount_number());
            ps.setString(2, newAccount.getAccount_type());
            ps.setString(3, newAccount.getFirst_date());
            ps.setString(4, newAccount.getLast_date());
            ps.setDouble(5, newAccount.getInterest());
            ps.setDouble(6, newAccount.getTotal());
            ps.setInt(7, newAccount.getCustomer_id_1());
            ps.setInt(8, newAccount.getCustomer_id_2());;

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

            String sql = "select * from account";
            Statement s = conn.createStatement();

            // generally not used
            // ResultSet rs = conn.createStatement().executeQuery("select * from account")

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                // column label must match table column name

                account.setAccount_id(    rs.getInt(   "account_id"  ));
                account.setAccount_number(rs.getString("account_number"       ));
                account.setAccount_type(  rs.getString("account_type"      ));
                account.setFirst_date(    rs.getString("first_date"       ));
                account.setLast_date(rs.getString("last_date"));
                account.setInterest(      rs.getDouble("interest"));
                account.setTotal(         rs.getDouble("total"));
                account.setCustomer_id_1( rs.getInt("customer_id_1"));
                account.setCustomer_id_2( rs.getInt("customer_id_2"));

                accountList.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return accountList;

    }


    public List<Account> findAll(String customer_id) throws IOException {
        logger.info("AccountDao::findAll() : finding all accounts with customer_id: " + customer_id);

        // FileWriter's evil counterpart, to read files

        List<Account> accountList = new LinkedList<>();
        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            //String sql = "select * from account_info where customer_id_1 = ? or customer_id_2 = ?";
            String sql = "select * from account where customer_id_1 = ? or customer_id_2 = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(customer_id));
            ps.setInt(2, Integer.parseInt(customer_id));


            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // the last line of the file is null
                Account account = new Account();

                // column lable must match table column name
                account.setAccount_id(    rs.getInt(   "account_id"  ));
                account.setAccount_number(rs.getString("account_number"       ));
                account.setAccount_type(  rs.getString("account_type"      ));
                account.setFirst_date(    rs.getString("first_date"       ));
                account.setLast_date(rs.getString("last_date"));
                account.setInterest(      rs.getDouble("interest"));
                account.setTotal(         rs.getDouble("total"));
                account.setCustomer_id_1( rs.getInt("customer_id_1"));
                account.setCustomer_id_2( rs.getInt("customer_id_2"));

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

                // column label must match table column name
                account.setAccount_id(    rs.getInt(   "account_id"  ));
                account.setAccount_number(rs.getString("account_number"       ));
                account.setAccount_type(  rs.getString("account_type"       ));
                account.setFirst_date(    rs.getString("first_date"       ));
                account.setLast_date(     rs.getString("last_date"      ));
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
    public Account findById(String account_number) {
        logger.info("AccountDao::findById() : find a account by account_number: " + account_number);


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            //String sql = "select * from account_info where account_number = ?";
            String sql = "select * from account where account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account_number); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            if(!rs.next()){
                throw new ResourcePersistenceException("This account was not found in the database, please check ID entered was correct.");
            }

            Account account = new Account();

            account.setAccount_id(    rs.getInt(   "account_id"  ));
            account.setAccount_number(rs.getString("account_number"       ));
            account.setAccount_type(  rs.getString("account_type"       ));
            account.setFirst_date(    rs.getString("first_date"       ));
            account.setLast_date(     rs.getString("last_date"      ));
            account.setInterest(      rs.getDouble("interest"));
            account.setTotal(         rs.getDouble("total"));
            account.setCustomer_id_1( rs.getInt("customer_id_1"));
            account.setCustomer_id_2( rs.getInt("customer_id_2"));


            return account;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double findTotal(String account_number) {
        logger.info("AccountDao::findTotal() : find a account by account_number: " + account_number);

        double total = 0.00;
        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select total from account where account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account_number); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            if(!rs.next()){
                throw new ResourcePersistenceException("This account was not found in the database, please check account_number entered was correct.");
            }

             total = rs.getDouble("total");

            return total;

        } catch (SQLException e) {
            e.printStackTrace();
            return total;
        }

    }

    // This doesn't work
    public boolean updateBalance(String account_number) {
        logger.info("AccountDao::updateBalance() : update a amount with account_number: "  + account_number);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            //String sql = "update account set total = ? where account_number = ?";
            String sql = "UPDATE account a" +
                        "    SET total = t.tot_balance" +
                        "FROM (SELECT account_number," +
                                "SUM( case " +
                                " when trans_type = 'deposit' then ABS(t.amount) " +
                                " when trans_type = 'transfer' then -ABS(t.amount) " +
                                " when trans_type = 'withdraw' then -ABS(t.amount) " +
                                " else 0.00 " +
                                " end " +
                            " ) as tot_balance" +
                            " FROM trans t" +
                            " where account_number = ? " +
                            " GROUP BY account_number" +
                        " ) t" +
                        "WHERE a.account_number = t.account_number";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account_number);

            int checkUpdate = ps.executeUpdate();
            if (checkUpdate == 0) {
                throw new RuntimeException();
            } else return true;

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBalance(double totalBalance, String account_number) {
        logger.info("AccountDao::updateBalance() : update a amount with account_number: "  + account_number);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            //String sql = "update account set total = ? where account_number = ?";
            String sql = "update account SET total = ? where account_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDouble(1, totalBalance);
            ps.setString(2, account_number);

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
    public boolean update(Account updateAccount) {
        logger.info("AccountDao::update() : update an account" + updateAccount);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set account_type = ?, last_date = ?, interest = ?, total = ? where account_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updateAccount.getAccount_type());
            ps.setString(2, updateAccount.getLast_date());
            ps.setDouble(3, updateAccount.getInterest());
            ps.setDouble(4, updateAccount.getTotal());
            ps.setString(5, updateAccount.getAccount_number());

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
    public boolean delete(String account_number) {
        logger.info("AccountDao::delete() : delete a account by account_number");
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "delete from account where account_number = ?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account_number);

            int checkUpdate = ps.executeUpdate();
            if (checkUpdate == 0) {
                throw new RuntimeException();
            } else return true;

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean close(String account_number) {
        logger.info("AccountDao::close() : close an account by account_number");
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set account_type = ?, last_date = ? where account_number = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "closed");
            ps.setString(2, LocalDate.now().toString());
            ps.setString(3, account_number);

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
