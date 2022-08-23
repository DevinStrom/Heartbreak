/*
Devin Strom
Prof. Charnesky
CIS 2454
Final Project
 */

 /*
This project is based heavily on the sample project from CIS 2454 Summer 2022 lectures weeks 12 & 13 by Prof. Charnesky
 */
package heartbreak;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LikeModel {

    public static ArrayList<Like> getLikeCount() {
        ArrayList<Like> likes = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM tbl_like");

            while (results.next()) {
                int id = results.getInt("id");
                int tweet_id = results.getInt("tweet_id");
                int user_id = results.getInt("user_id");
                boolean clmnLike = results.getBoolean("clmn_like");

                Like like = new Like(id, tweet_id, user_id, clmnLike);
                likes.add(like);
            }

            results.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //return the tweets arraylist
        return likes;
    }

    //add like by user_id
    public void addLike(int tweet_id, int user_id, String like) {

        try {
            if ("like".equals(like)) {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO tbl_like(tweet_id, user_id, clmn_like) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setInt(1, tweet_id);
                statement.setInt(2, user_id);
                statement.setBoolean(3, true);

                statement.execute();
                statement.close();
                connection.close();
            } else if ("dislike".equals(like)) {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO tbl_like(tweet_id, user_id, clmn_like) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setInt(1, tweet_id);
                statement.setInt(2, user_id);
                statement.setBoolean(3, false);

                statement.execute();
                statement.close();
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Likes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //add like by username
    public void addLike(int tweet_id, String username, String like) {

        try {
            if ("like".equals(like)) {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO tbl_like(tweet_id, user_id, clmn_like) VALUES (?, (SELECT user.id FROM user WHERE user.username=?), ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setInt(1, tweet_id);
                statement.setString(2, username);
                statement.setBoolean(3, true);

                statement.execute();
                statement.close();
                connection.close();
            } else if ("dislike".equals(like)) {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO tbl_like(tweet_id, user_id, clmn_like) VALUES (?, (SELECT user.id FROM user WHERE user.username=?), ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setInt(1, tweet_id);
                statement.setString(2, username);
                statement.setBoolean(3, false);

                statement.execute();
                statement.close();
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Likes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addLikeValues(String username) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            //source for getting last row in table: https://stackoverflow.com/questions/5191503/how-to-select-the-last-record-of-a-table-in-sql
            //answer by Adriaan Stander, 3-4-11
            String query = "INSERT INTO tbl_like(tweet_id, user_id, clmn_like) VALUES ((SELECT id FROM tweet ORDER BY id DESC LIMIT 1), (SELECT user.id FROM user WHERE user.username=?), null)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, username);

            statement.execute();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Likes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LikeModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
