package com.revature.richbank.dos;

import com.revature.richbank.exceptions.ResourcePersistenceException;
import com.revature.richbank.models.Customer;
import com.revature.richbank.util.ConnectionFactory;
import com.revature.richbank.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDao implements Crudable<Customer> {

    private Logger logger = Logger.getLogger();


    @Override
    public Customer create(Customer newCustomer) {
        logger.info("CustomerDao::create() : creating new customer");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into customer values ("

            String sql = "insert into customer ( customer_id, customer_name, email_1, phone_1, address, login_id, login_password, block ) values (default, ?, ?, ?, ?, ?, ?, default)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newCustomer.getCustomer_name());
            ps.setString(2, newCustomer.getEmail_1());
            ps.setString(3, newCustomer.getPhone_1());
            ps.setString(4, newCustomer.getAddress());
            ps.setString(5, newCustomer.getLogin_id());
            ps.setString(6, newCustomer.getLogin_password());

            int checkInsert = ps.executeUpdate();
            if (checkInsert == 0) {
                throw new ResourcePersistenceException("Customer was not entered into database due to some issue.");
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }

        return newCustomer;
    }


    @Override
    //public Customer[] findAll() throws IOExcetpion {
    public List<Customer> findAll()  throws IOException{
        logger.info("CustomerDao::findAll() : finding all customers");

        // FileWriter's evil counterpart, to read files

        List<Customer> customerList = new LinkedList<>();

        //connection is auto closable
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from customer";
            Statement s = conn.createStatement();

            // generally not used
            // ResultSet rs = conn.createStatement().executeQuery("select * from customer")

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                Customer customer = new Customer();

                // column lable must match table column name
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setEmail_1(rs.getString("email_1"));
                customer.setPhone_1(rs.getString("phone_1"));
                customer.setAddress(rs.getString("address"));
                customer.setLogin_id(rs.getString("login_id"));
                customer.setLogin_password(rs.getString("login_password"));
                customer.setBlock(rs.getBoolean("block"));

                customerList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Returning customers information to user.");
        return customerList;
    }



    @Override
    public Customer findById(String login_id) {

        logger.info("CustomerDao::findById() : find a customer by login_id: [" + login_id + "]");


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from customer where login_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id); // Wrapper class
           // boolean canFind = ps.execute();

            //System.out.println("canFind: " + canFind);
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            if(!rs.next()){
                throw new ResourcePersistenceException("Customer was not found in the database, please check ID entered was correct.");
            }
            Customer customer = new Customer();

            // column label must match table column name
            customer.setCustomer_id(rs.getInt("customer_id"));
            customer.setCustomer_name(rs.getString("customer_name"));
            customer.setEmail_1(rs.getString("email_1"));
            customer.setPhone_1(rs.getString("phone_1"));
            customer.setAddress(rs.getString("address"));
            customer.setLogin_id(rs.getString("login_id"));
            customer.setLogin_password(rs.getString("login_password"));
            customer.setBlock(rs.getBoolean("block"));

            return customer;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Customer findById(String login_id, String login_password) {

        logger.info("CustomerDao::findById() : find a customer by login_id and login_password");


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from customer where login_id = ? and login_password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id); // Wrapper class
            ps.setString(2, login_password); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            Customer customer = new Customer();

            System.out.println("CustomerDao::findById() : with id and password got a ResultSet");
            if ( rs.next() ) {
                // column label must match table column name
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setEmail_1(rs.getString("email_1"));
                customer.setPhone_1(rs.getString("phone_1"));
                customer.setAddress(rs.getString("address"));
                customer.setLogin_id(rs.getString("login_id"));
                customer.setLogin_password(rs.getString("login_password"));
                customer.setBlock(rs.getBoolean("block"));

            }
            return customer;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public boolean update(Customer updateCustomer) {

        logger.info("CustomerDao::update() : update a customer by login_id" + updateCustomer);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into customer values ("

            String sql = "update customer set customer_name = ?, email_1 = ?, phone_1 = ?, address = ?, login_password = ?, block = ? where login_id = ?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updateCustomer.getCustomer_name());
            ps.setString(2, updateCustomer.getEmail_1());
            ps.setString(3, updateCustomer.getPhone_1());
            ps.setString(4, updateCustomer.getAddress());
            ps.setString(5, updateCustomer.getLogin_password());
            ps.setBoolean(6, updateCustomer.isBlock());
            ps.setString(7, updateCustomer.getLogin_id());

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
    public boolean delete (String login_id) {

        logger.info("CustomerDao::delete() : delete a customer by login_id");
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into customer values ("

            //String sql = "update customer set block=true where login_id = ?";
            String sql = "delete from customer where login_id = ?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id);

            int checkUpdate = ps.executeUpdate();
            if (checkUpdate == 0) {
                throw new RuntimeException();
            } else return true;

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void checkLogin_ID (String login_id) {

        logger.info("CustomerDao::checkLogin_ID() : find a customer by login_id");

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select login_id from customer where login_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query
System.out.println("CustomerDao::checkLogin_ID() : got a ResultSet");
            if ( rs.next() ) {
                System.out.println("Same login ID is already exist!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkCustomer_ID(String login_id){
        logger.info("CustomerDao::checkCustomer_ID(): find a customer_id by login_id");

        int customer_id = 0;
        try( Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select customer_id from customer where login_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
                customer_id = rs.getInt("customer_id");
            return customer_id;

        } catch (SQLException e) {
            e.getStackTrace();
            return customer_id;
        }
    }

    public Customer authenticateCustomer(String login_id, String login_password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from customer where login_id = ? and login_password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id); // Wrapper class
            ps.setString(2, login_password); // Wrapper class

            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            if( !rs.next() )
                return null;

            Customer customer = new Customer();

            // column label must match table column name
            customer.setCustomer_id(rs.getInt("customer_id"));
            customer.setCustomer_name(rs.getString("customer_name"));
            customer.setEmail_1(rs.getString("email_1"));
            customer.setPhone_1(rs.getString("phone_1"));
            customer.setAddress(rs.getString("address"));
            customer.setLogin_id(rs.getString("login_id"));
            customer.setLogin_password(rs.getString("login_password"));
            customer.setBlock(rs.getBoolean("block"));

             return customer;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
