package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DateBaseUser_chat_id implements DateBaseCommands{
    private String user_chat_id;

    public String getDataBase(Connection connection) throws SQLException {
        String pois = "select (chat_id) from telegrambot where (chat_id = "+user_chat_id+");";
        connection.setAutoCommit(false);
        PreparedStatement statement1 = connection.prepareStatement(pois);
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()){
            user_chat_id = (resultSet.getString("chat_id"));
        }
        connection.commit();
        connection.close();
        return user_chat_id;
    }

    public String getDataBase(Connection connection, String user_chat_id) throws SQLException {

        String pois = "select (chat_id) from telegrambot where (chat_id = "+user_chat_id+");";
        connection.setAutoCommit(false);
        PreparedStatement statement1 = connection.prepareStatement(pois);
        ResultSet resultSet = statement1.executeQuery();
        if (!resultSet.next()){
            user_chat_id = null;
        }
        while (resultSet.next()){
            user_chat_id = (resultSet.getString("chat_id"));
        }
        connection.commit();
        connection.close();
        return user_chat_id;
    }

    public void setDataBase(Connection connection, String user_chat_id, String name_or_chat_or_text) throws SQLException {
        String qur = "with x as (select "+user_chat_id+" as chat_id , '"+ name_or_chat_or_text +"' as users)\n" +
                "insert into telegrambot (chat_id,users)(select * from x where not exists(select * from telegrambot c where x.chat_id=c.chat_id));";
        String sasd = "update telegrambot  \n" +
                "set users = '"+ name_or_chat_or_text +"'\n" +
                "where chat_id  = "+user_chat_id+";";
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement(qur);
        statement.executeUpdate();
        PreparedStatement statement2 = connection.prepareStatement(sasd);
        statement2.executeUpdate();
        connection.commit();
        connection.close();
        this.user_chat_id = user_chat_id;
    }

    @Override
    public List<String> getGroup(Connection connection) throws SQLException {
        return null;
    }
}
