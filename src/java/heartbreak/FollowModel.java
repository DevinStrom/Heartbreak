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

public class FollowModel {

    public boolean checkFollowed(String followedByUser, String userFollowed) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT id FROM following WHERE followed_by_user_id = (SELECT id FROM user WHERE username=?) "
                    + "AND following_user_id = (SELECT id FROM user WHERE username=?);";

            //santize data before user gives it database, avoid sql injection
            PreparedStatement statement = connection.prepareStatement(query);

            // indexing starts with 1
            statement.setString(1, followedByUser);
            statement.setString(2, userFollowed);

            ResultSet results = statement.executeQuery();
            Boolean result = false;
            if (results.next()) {
                result = true;
            }

            results.close();
            statement.close();
            connection.close();

            return result; //if no result, remains false

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static void updateFollow(String followedByUser, String userFollowed, String follow) {
        try {
            if (follow.equals("follow")) {
                Connection connection = DatabaseConnection.getConnection();
                String query = "INSERT INTO following(followed_by_user_id, following_user_id) "
                        + "VALUES ((SELECT id FROM user WHERE username=?),(SELECT id FROM user WHERE username=?))";

                //santize data before user gives it database, avoid sql injection
                PreparedStatement statement = connection.prepareStatement(query);

                // indexing starts with 1
                statement.setString(1, followedByUser);
                statement.setString(2, userFollowed);

                statement.execute();
                statement.close();
                connection.close();
            } else if (follow.equals("unfollow")) {
                Connection connection = DatabaseConnection.getConnection();
                String query = "DELETE FROM following WHERE followed_by_user_id = (SELECT id FROM user WHERE username=?)"
                        + " AND following_user_id = (SELECT id FROM user WHERE username=?)";

                //santize data before user gives it database, avoid sql injection
                PreparedStatement statement = connection.prepareStatement(query);

                // indexing starts with 1
                statement.setString(1, followedByUser);
                statement.setString(2, userFollowed);

                statement.execute();
                statement.close();
                connection.close();
            } else {
                //do nothing, I was too scared to leave one of the other options as just an else { } statement in case they accidentally executed 
                //over something I didn't catch
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
