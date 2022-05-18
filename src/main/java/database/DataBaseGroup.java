package database;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseGroup {
    private final PoolDateBase poolDateBase = new PoolDateBase();
    private final DataSource dataSource = new DataSource();

    public  List<String> group_id = new ArrayList<>();

    public List<String> getGroup() {
        dataSource.setPoolProperties(poolDateBase.getP());
        String pois = "select (id_group) from telegrambot_chat_id;";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return group_id;
    }

    public void setGroup_id(String group_id) {
        dataSource.setPoolProperties(poolDateBase.getP());

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
