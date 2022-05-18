package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DataBaseUser_group implements DateBaseCommands{
    private String user_chat_id;
    private String name_or_chat;

    @Override
    public String getDataBase(Connection connection) throws SQLException {
        String pois = "select * from group_id where (chat_id = "+user_chat_id+" and user_group = '"+ name_or_chat +"' );";
        connection.setAutoCommit(false);
        PreparedStatement statement1 = connection.prepareStatement(pois);
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()){
            name_or_chat = resultSet.getString("user_group");
        }
        connection.commit();
        connection.close();
        return name_or_chat;
    }

    @Override
    public String getDataBase(Connection connection, String user_chat_id) throws SQLException {
        return null;
    }

    @Override
    public void setDataBase(Connection connection, String user_chat_id, String name_or_chat_or_text) throws SQLException {
        String qur = "with x as (select "+user_chat_id+"  as chat_id , "+ name_or_chat_or_text +" as user_group)\n" +
                "insert into group_id (chat_id,user_group)" +
                "(select * from x where not exists(select * from group_id t where x.chat_id = t.chat_id));";
        String sasd = "UPDATE group_id \n" +
                "set user_group = '"+ name_or_chat_or_text +"'\n" +
                "where chat_id  = '"+user_chat_id+"';";
        connection.setAutoCommit(false);
        PreparedStatement statement2 = connection.prepareStatement(qur);
        statement2.executeUpdate();
        PreparedStatement statement = connection.prepareStatement(sasd);
        statement.executeUpdate();
        connection.commit();
        connection.close();
        this.user_chat_id = user_chat_id;
        this.name_or_chat = name_or_chat_or_text;
    }

    @Override
    public List<String> getGroup(Connection connection) throws SQLException {
        return null;
    }
}
