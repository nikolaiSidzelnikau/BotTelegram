package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class JDBCPostgreSQLExample {

    final static Logger logger = LoggerFactory.getLogger(JDBCPostgreSQLExample.class);

    //  Database credentials
    static final String DB_URL = "";
    static final String USER = "";
    static final String PASS = "";
    public String text;

    public String getText(String chat_id) {
        textUserBot(chat_id);
        return text;
    }

    public void textUserBot(String chat_id){
        String pois = "select (user_text) from text_id where (chat_id = '"+chat_id+"' );";
        try (Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareCall(pois);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                text = resultSet.getString("user_text");
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setRecord(String chat_id, String text_user) {
        String qur = "with x as (select "+chat_id+" as chat_id , '"+text_user+"' as user_text)\n" +
                "insert into text_id (chat_id,user_text)(select * from x where not exists(select * from text_id c where x.chat_id=c.chat_id));";
        String pois = "select * from text_id where (chat_id = "+chat_id+" and user_text = '"+text_user+"' );";
        String delete = "delete FROM text_id  WHERE chat_id = '"+chat_id+"' AND users = '"+text_user+"';";
        String sasd = "UPDATE text_id \n" +
                "set user_text = '"+text_user+"'\n" +
                "where chat_id  = '"+chat_id+"';";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(sasd);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("chat_id");
                String name = resultSet.getString("user_text");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void getRecord(String chat_id, String users) {
        String qur = "with x as (select "+chat_id+" as chat_id , '"+users+"' as users)\n" +
                "insert into telegrambot (chat_id,users)(select * from x where not exists(select * from telegrambot c where x.chat_id=c.chat_id));";
       String pois = "select * from telegrambot where (chat_id = "+chat_id+" and users = '"+users+"' );";
        //String idUser = "INSERT INTO telegrambot(chat_id,users) values ('" + chat_id + "','" + users + "');";
        try (Connection connection =DriverManager.getConnection(DB_URL, USER, PASS);){
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(qur);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("chat_id");
                String name = resultSet.getString("users");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
