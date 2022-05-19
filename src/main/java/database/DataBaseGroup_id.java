package database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseGroup_id implements DateBaseCommands{
    public  List<String> group_id = new ArrayList<>();
    private int group = 0;
    final static Logger logger = LoggerFactory.getLogger(DataBaseGroup_id.class);

    @Override
    public String getDataBase(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public String getDataBase(Connection connection, String user_chat_id) throws SQLException {
        return null;
    }

    @Override
    public void setDataBase(Connection connection, String user_chat_id, String name_or_chat_or_text) throws SQLException {
        String qur = "with x as (select "+name_or_chat_or_text+" as id_group)\n" +
                "insert into telegrambot_chat_id (id_group) " +
                "(select * from x where not exists(select * from telegrambot_chat_id c where x.id_group=c.id_group ));";
        PreparedStatement statement2 = connection.prepareStatement(qur);
        statement2.executeUpdate();
    }

    @Override
    public List<String> getGroup(Connection connection) throws SQLException {
        String pois = "select (id_group) from telegrambot_chat_id;";
        PreparedStatement statement1 = connection.prepareStatement(pois);
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()){
            group_id.add(resultSet.getString("id_group"));
            group++;
        }
        logger.info(String.valueOf(group));
        return group_id;
    }

}
