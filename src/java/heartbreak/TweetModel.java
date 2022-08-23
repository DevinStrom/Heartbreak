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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TweetModel {

    public static ArrayList<Tweet> getTweets() {
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM tweet");

            while (results.next()) {
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                int user_id = results.getInt("user_id");
                Tweet tweet = new Tweet(id, text, timestamp, user_id);
                tweets.add(tweet);
            }

            results.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //return the tweets arraylist
        return tweets;
    }

    public static ArrayList<Tweet> getTweetsWithUsernameFromUsers() { 
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT tweet.id, tweet.text, tweet.timestamp, tweet.user_id, user.username, "
                    + "COUNT(case when tbl_like.clmn_like = 1 then tbl_like.clmn_like end) AS 'tweet_likes', "
                    + "COUNT(case when tbl_like.clmn_like = 0 then tbl_like.clmn_like end) AS 'tweet_dislikes' "
                    + "FROM tweet INNER JOIN user ON tweet.user_id=user.id INNER JOIN tbl_like "
                    + "ON tweet.id=tbl_like.tweet_id GROUP BY tweet.id, 'tweet_likes', 'tweet_dislikes'"
                    + "ORDER BY tweet.id DESC");
            while (results.next()) {
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                int user_id = results.getInt("user_id");
                int tweet_likes = results.getInt("tweet_likes");
                int tweet_dislikes = results.getInt("tweet_dislikes");
                String username = results.getString("username");

                Tweet tweet = new Tweet(id, text, timestamp, user_id, username, tweet_likes, tweet_dislikes);
                tweets.add(tweet);
            }

            results.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //return the tweets arraylist
        return tweets;
    }

    public static ArrayList<Tweet> getTweetsFromSingleUser(String username) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT tweet.id, tweet.text, tweet.timestamp, tweet.user_id, user.username, "
                    + "COUNT(case when tbl_like.clmn_like = 1 then tbl_like.clmn_like end) AS 'tweet_likes', " 
                    + "COUNT(case when tbl_like.clmn_like = 0 then tbl_like.clmn_like end) AS 'tweet_dislikes' " 
                    + "FROM tweet INNER JOIN user ON tweet.user_id=user.id INNER JOIN tbl_like ON tweet.id=tbl_like.tweet_id "
                    + "WHERE user.username='" + username + "' GROUP BY tweet.id, 'tweet_likes', 'tweet_dislikes'"
                    + "ORDER BY tweet.id DESC");
            while (results.next()) {
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                int user_id = results.getInt("user_id");
                int tweet_likes = results.getInt("tweet_likes");
                int tweet_dislikes = results.getInt("tweet_dislikes");
                String resultUsername = results.getString("username");
                Tweet tweet = new Tweet(id, text, timestamp, user_id, resultUsername, tweet_likes, tweet_dislikes);
                tweets.add(tweet);
            }

            results.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //return the tweets arraylist
        return tweets;
    }

    public static ArrayList<Tweet> getFollowedTweetsWithUsernameFromUsers(String sessionUsername) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT tweet.id, tweet.text, tweet.timestamp, tweet.user_id, user.username, "
                    + "COUNT(case when tbl_like.clmn_like = 1 then tbl_like.clmn_like end) AS 'tweet_likes', "
                    + "COUNT(case when tbl_like.clmn_like = 0 then tbl_like.clmn_like end) AS 'tweet_dislikes' "
                    + "FROM tweet "
                    + "INNER JOIN user ON tweet.user_id=user.id "
                    + "INNER JOIN tbl_like ON tweet.id=tbl_like.tweet_id "
                    + "INNER JOIN following "
                    + "WHERE followed_by_user_id = (SELECT id FROM user WHERE username = '" + sessionUsername + "') AND following_user_id = tweet.user_id "
                    + "GROUP BY tweet.id, 'tweet_likes', 'tweet_dislikes' ORDER BY tweet.id DESC; ");

            while (results.next()) {
                int id = results.getInt("id");
                String text = results.getString("text");
                Timestamp timestamp = results.getTimestamp("timestamp");
                int user_id = results.getInt("user_id");
                int tweet_likes = results.getInt("tweet_likes");
                int tweet_dislikes = results.getInt("tweet_dislikes");
                String username = results.getString("username");
                Tweet tweet = new Tweet(id, text, timestamp, user_id, username, tweet_likes, tweet_dislikes);
                tweets.add(tweet);
            }

            results.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //return the tweets arraylist
        return tweets;
    }

    public static void addTweet(String text, String sessionUsername) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO tweet(text, user_id) VALUES (?, (SELECT id from user where username = ?))";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, text);
            statement.setString(2, sessionUsername);

            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
