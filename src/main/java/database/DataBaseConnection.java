package database;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataBaseConnection {
    private String textConnection;
    private final PoolDateBase poolDateBase = new PoolDateBase();
    private final DataSource dataSource = new DataSource();
    DateBaseCommands dateBaseUser_chat_id = new DateBaseUser_chat_id();
    DateBaseCommands dateBaseUser_group = new DataBaseUser_group();
    DateBaseCommands dateBaseUser_text = new DatabaseUser_text();
    DateBaseCommands dataBaseGroup_id = new DataBaseGroup_id();
    public List<String> group = new ArrayList<>();

    public List<String> getGroup() {
        return group;
    }

    public String getConnections(String chat_id, String name_or_chat_or_text, int number) {
        dataSource.setPoolProperties(poolDateBase.getP());
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            switch (number){
                case 1:
                    textConnection = dateBaseUser_chat_id.getDataBase(connection,chat_id);
                    break;
                case 2:
                    dateBaseUser_chat_id.setDataBase(connection,chat_id,name_or_chat_or_text);
                    break;
                case 3:
                    textConnection = dateBaseUser_group.getDataBase(connection);
                    break;
                case 4:
                    dateBaseUser_group.setDataBase(connection,chat_id,name_or_chat_or_text);
                    break;
                case 5:
                    for (String s: dataBaseGroup_id.getGroup(connection)) {
                        getGroup().add(s);
                    }
                    break;
                case 6:
                   dataBaseGroup_id.setDataBase(connection,chat_id,name_or_chat_or_text);
                    break;
                case 7:
                    textConnection = dateBaseUser_text.getDataBase(connection);
                    break;
                case 8:
                    dateBaseUser_text.setDataBase(connection,chat_id,name_or_chat_or_text);
                    break;
                case 9:
                   textConnection = dateBaseUser_chat_id.getDataBase(connection);
                    break;
            }
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return textConnection;
    }
}

