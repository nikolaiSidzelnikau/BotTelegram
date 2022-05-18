package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DateBaseCommands {
     String getDataBase(Connection connection) throws SQLException;
     String getDataBase(Connection connection, String user_chat_id) throws SQLException;
     void setDataBase(Connection connection, String user_chat_id, String name_or_chat_or_text) throws SQLException;
     List<String> getGroup(Connection connection) throws SQLException;
}
