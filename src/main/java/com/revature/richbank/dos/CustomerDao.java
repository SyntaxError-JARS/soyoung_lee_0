package com.revature.richbank.dos;

import com.revature.richbank.models.Customer;
import com.revature.richbank.util.ConnectionFactory;

import java.sql.*;

public class CustomerDao implements Crudable<Customer> {


    @Override
    public Customer create(Customer newCustomer) {
        System.out.println("CustomerDao::create() : creating new customer");

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into customer values ("

            String sql = "insert into customer ( customer_name, email_1, phone_1, address, login_id, login_password ) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, newCustomer.getCustomer_name());
            ps.setString(2, newCustomer.getEmail_1());
            ps.setString(3, newCustomer.getPhone_1());
            ps.setString(4, newCustomer.getAddress());
            ps.setString(5, newCustomer.getLogin_id());
            ps.setString(6, newCustomer.getLogin_password());

            int checkInsert = ps.executeUpdate();
            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }

        return newCustomer;
    }


    @Override
    //public Customer[] findAll() throws IOExcetpion {
    public Customer[] findAll() {
        System.out.println("CustomerDao::findAll() : finding all customers");

        // FileWriter's evil counterpart, to read files

        Customer[] customers = new Customer[10];
        int index = 0;
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
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setEmail_1(rs.getString("email_1"));
                customer.setPhone_1(rs.getString("phone_1"));
                customer.setAddress(rs.getString("address"));
                customer.setLogin_id(rs.getString("login_id"));
                customer.setLogin_password(rs.getString("login_password"));

                System.out.println("Going to the next line for our following index.");
                customers[index] = customer;
                index++; // increment the index by 1, must occur after the trainer[index] re-assignment
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Returning customers information to user.");
        return customers;
    }



    @Override
    public Customer findById(String login_id) {

        System.out.println("CustomerDao::findById() : find a customer by login_id");


        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {
            String sql = "select * from customer where login_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, login_id); // Wrapper class
            ResultSet rs = ps.executeQuery(); // remember DQL, select is only keywords for Query

            Customer customer = new Customer();

            // column label must match table column name
            customer.setCustomer_id(Integer.toString(rs.getInt("customer_id")));
            customer.setCustomer_name(rs.getString("customer_name"));
            customer.setEmail_1(rs.getString("email_1"));
            customer.setPhone_1(rs.getString("phone_1"));
            customer.setAddress(rs.getString("address"));
            customer.setLogin_id(rs.getString("login_id"));
            customer.setLogin_password(rs.getString("login_password"));
            customer.setBlock(rs.getBoolean("block"));

            return customer;

        } catch (SQLException e) {
            return null;
        }

    }

    public Customer findById(String login_id, String login_password) {

        System.out.println("CustomerDao::findById() : find a customer by login_id and password");


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
                customer.setCustomer_id(Integer.toString(rs.getInt("customer_id")));
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

        System.out.println("CustomerDao::update() : update a customer by login_id" + updateCustomer);

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // YOU NEVER NEVER NEVER USE THIS DIRECT STATEMENT
            // Because of SQL INJECTION
            // String sql = "insert into customer values ("

            String sql = "update customer set customer_name = ?, email_1 = ?, phone_1 = ?, address = ?, login_password = ? where login_id = ?";


            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, updateCustomer.getCustomer_name());
            ps.setString(2, updateCustomer.getEmail_1());
            ps.setString(3, updateCustomer.getPhone_1());
            ps.setString(4, updateCustomer.getAddress());
            ps.setString(5, updateCustomer.getLogin_password());
            ps.setString(6, updateCustomer.getLogin_id());

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

        System.out.println("CustomerDao::delete() : delete a customer by login_id");

        return false;
    }

    public void checkLogin_ID (String login_id) {

        System.out.println("CustomerDao::checkLogin_ID() : find a customer by login_id");

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
}
