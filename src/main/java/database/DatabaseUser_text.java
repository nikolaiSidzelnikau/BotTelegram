package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUser_text implements DateBaseCommands{
    private String user_text;
    private String user_chat_id;

    @Override
    public String getDataBase(Connection connection) throws SQLException {
        String search = "select (user_text) from text_id ti where (chat_id = "+user_chat_id+");";
        PreparedStatement statement1 = connection.prepareStatement(search);
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()){
            user_text = resultSet.getString("user_text");
        }
        return user_text;
    }

    @Override
    public String getDataBase(Connection connection, String user_chat_id) throws SQLException {
        String search = "select (user_text) from text_id ti where (chat_id = "+user_chat_id+");";
        PreparedStatement statement1 = connection.prepareStatement(search);
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()){
            user_text = resultSet.getString("user_text");
        }
        return user_text;
    }

    @Override
    public void setDataBase(Connection connection, String user_chat_id, String name_or_chat_or_text) throws SQLException {
        String qur = "with x as (select "+user_chat_id+" as chat_id , ? as user_text)\n" +
                "insert into text_id (chat_id,user_text)(select * from x where not exists(select * from text_id c where x.chat_id=c.chat_id));";
        String sasd = "UPDATE text_id \n" +
                "set user_text = ?\n" +
                "where chat_id  = '"+user_chat_id+"';";
        PreparedStatement statement2 = connection.prepareStatement(qur);
        statement2.setString(1,name_or_chat_or_text);
        statement2.executeUpdate();
        PreparedStatement statement = connection.prepareStatement(sasd);
        statement.setString(1,name_or_chat_or_text);
        statement.executeUpdate();
        this.user_chat_id = user_chat_id;
        this.user_text = name_or_chat_or_text;
    }

    @Override
    public List<String> getGroup(Connection connection) throws SQLException {
        return null;
    }
}
