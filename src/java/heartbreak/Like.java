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


public class Like {
    private int id;
    private int tweet_id;
    private int user_id;
    private boolean like;
    
    public Like() {  
    }

    public Like(int id, int tweet_id, int user_id, boolean like) {
        this.id = id;
        this.tweet_id = tweet_id;
        this.user_id = user_id;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(int tweet_id) {
        this.tweet_id = tweet_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }


}
