/*
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
 */

 /*
This project is based heavily on the sample project from CIS 2454 Summer 2022 lectures weeks 12 & 13 by Prof. Charnesky
 */

//"user class represents a single user, usermodel is how we talk to it" - JSP Database Connectivity - Winter 2022 23:52
//these crud operations were based heavily(read: entirely) on week 12 and 13 lecture videos, CIS 2454, Summer 2022, Prof. Charnesky

package heartbreak;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class UserModel {
    
    
    public static boolean login(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT id, username, password FROM user WHERE username = ?";
            
            //santize data before user gives it database, avoid sql injection
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1
            statement.setString(1, user.getUsername());
            
            ResultSet results = statement.executeQuery();           
            String password = "";
            
            //only one result coming back
            if(results.next()) {
                password = results.getString("password");
            }
            
            results.close();
            statement.close();
            connection.close();
            
            //essesntially, if passwords match, return true, elese return false
            return !password.isEmpty() && user.getPassword().equals(password);
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM user");
            while(results.next()) {
                int id = results.getInt("id");
                String username = results.getString("username");
                String password = results.getString("password");
                String filename = results.getString("filename");
                
                User user = new User(id, username, password, filename);
                
                users.add(user);
            }
            
            results.close();
            statement.close();
            connection.close();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return users;
    }
    
    public static void addUser(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO user (username, password) VALUES (?,?)";
            
            //santize data before user gives it database, avoid sql injection
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            
            statement.execute();
            
            statement.close();
            connection.close();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void updateUser(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "UPDATE user SET username = ?, password = ? WHERE id = ?";
            
            //santize data before user gives it database, avoid sql injection
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            
            statement.execute();
            
            statement.close();
            connection.close();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void deleteUser(User user){
        try{
            Connection connection = DatabaseConnection.getConnection();
            String query = "delete from user where id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            
            // indexing starts with 1
            statement.setInt(1, user.getId());
            statement.execute();
            statement.close();
            connection.close(); 
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }
    
    public static User getUser(String username) {
        User user = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "select id, username, password, filename from user where username = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet results = statement.executeQuery();
            
            while(results.next()) {  ////////////////////////////////////maybe change this to if(results.next()) { 
                int id = results.getInt("id");
                String password = results.getString("password");
                String filename = results.getString("filename");
                user = new User(id, username, password, filename);
            }
            
            results.close();
            statement.close();
            connection.close();  
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        
        return user;
    }
    
    
    
}
