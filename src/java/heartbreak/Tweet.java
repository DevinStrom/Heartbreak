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

import java.sql.Timestamp;

public class Tweet {
    private int id;
    private String text;
    private Timestamp timestamp;
    private int user_id;
    private int tweet_likes;
    private int tweet_dislikes;
    private String username;

    public Tweet() {

    }

    public Tweet(int id, String text, Timestamp timestamp, int user_id) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user_id = user_id;
    }

    public Tweet(int id, String text, Timestamp timestamp, int user_id, String username, int tweet_likes, int tweet_dislikes) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user_id = user_id;
        this.tweet_likes = tweet_likes;
        this.tweet_dislikes = tweet_dislikes;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getTweet_Likes() {
        return tweet_likes;
    }

    public int getTweet_Dislikes() {
        return tweet_dislikes;
    }

    public String getUsername() {
        return username;
    }
}

//
//will delete this after updating single user tweets to get likes and dislikes
//
//public Tweet(int id, String text, Timestamp timestamp, int user_id, String username) {
//    this.id = id;
//    this.text = text;
//    this.timestamp = timestamp;
//    this.user_id = user_id;
//    this.username = username;
    //}
