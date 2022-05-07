package database;

import java.sql.*;

public class JDBSDateBase {

    static final String DB_URL = "";
    static final String USER = "";
    static final String PASS = "";

    private String text_user;
    private String chat_id;
    private String user_name;
    private String user_group;

    public String getText_user() {
        return text_user;
    }

    public void setTextUserBot(String chat_id,String text_user) {
        String qur = "with x as (select "+chat_id+" as chat_id , '"+text_user+"' as user_text)\n" +
                "insert into text_id (chat_id,user_text)(select * from x where not exists(select * from text_id c where x.chat_id=c.chat_id));";
        String pois = "select * from text_id where (chat_id = "+chat_id+" and user_text = '"+text_user+"' );";
        String sasd = "UPDATE text_id \n" +
                "set user_text = '"+text_user+"'\n" +
                "where chat_id  = '"+chat_id+"';";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(sasd);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                this.text_user = resultSet.getString("user_text");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id,String user_name) {
        String qur = "with x as (select "+chat_id+" as chat_id , '"+user_name+"' as users)\n" +
                "insert into telegrambot (chat_id,users)(select * from x where not exists(select * from telegrambot c where x.chat_id=c.chat_id));";
        String pois = "select * from telegrambot where (chat_id = "+chat_id+" and users = '"+user_name+"' );";
        String sasd = "update telegrambot  \n" +
                "set users = '"+user_name+"'\n" +
                "where chat_id  = "+chat_id+";";
        try (Connection connection =DriverManager.getConnection(DB_URL, USER, PASS)){
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(qur);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sasd);
            statement2.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                this.chat_id = resultSet.getString("chat_id");
                this.user_name = resultSet.getString("users");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_group() {
        return user_group;
    }

    public void setUser_group(String chat_id, String user_group) {
        String qur = "with x as (select "+chat_id+"  as chat_id , "+user_group+" as user_group)\n" +
                "insert into group_id (chat_id,user_group)" +
                "(select * from x where not exists(select * from group_id t where x.chat_id = t.chat_id));";
        String pois = "select * from group_id where (chat_id = "+chat_id+" and user_group = '"+user_group+"' );";

        String sasd = "UPDATE group_id \n" +
                "set user_group = '"+user_group+"'\n" +
                "where chat_id  = '"+chat_id+"';";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(sasd);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                this.user_group = resultSet.getString("user_group");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
