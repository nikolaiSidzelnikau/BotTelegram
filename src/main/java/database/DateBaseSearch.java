package database;

import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DateBaseSearch implements JDBSDateBaseSearch{
    private String name_user;
    private String user_chat_id;
    private String user_text;
    private final List<String> group_id = new ArrayList<>();
    private String userGroup;
    private String idThread;

    private final PoolDateBase poolDateBase = new PoolDateBase();
    private final DataSource dataSource = new DataSource();

    @Override
    public String getName_user() {
        dataSource.setPoolProperties(poolDateBase.getP());
        String search = "select (users) from telegrambot  where (chat_id = "+user_chat_id+");";
        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(search);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                name_user = resultSet.getString("users");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return name_user;
    }

    @Override
    public void setName_user(String user_chat_id,String name_user) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String qur = "with x as (select "+user_chat_id+" as chat_id , '"+name_user+"' as users)\n" +
                "insert into telegrambot (chat_id,users)(select * from x where not exists(select * from telegrambot c where x.chat_id=c.chat_id));";
        String sasd = "update telegrambot  \n" +
                "set users = '"+name_user+"'\n" +
                "where chat_id  = "+user_chat_id+";";
        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(qur);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sasd);
            statement2.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getUser_chat_id(String user_chat_id) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String pois = "select (chat_id) from telegrambot where (chat_id = "+user_chat_id+");";
        try (Connection connection = dataSource.getConnection()) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user_chat_id;
    }
    @Override
    public String getUser_chat_id() {
        dataSource.setPoolProperties(poolDateBase.getP());
        String pois = "select (chat_id) from telegrambot where (chat_id = "+user_chat_id+");";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                user_chat_id = (resultSet.getString("chat_id"));
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user_chat_id;
    }

    @Override
    public void setUser_chat_id(String user_chat_id,String name_user) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String qur = "with x as (select "+user_chat_id+" as chat_id , '"+name_user+"' as users)\n" +
                "insert into telegrambot (chat_id,users)(select * from x where not exists(select * from telegrambot c where x.chat_id=c.chat_id));";
        String sasd = "update telegrambot  \n" +
                "set users = '"+name_user+"'\n" +
                "where chat_id  = "+user_chat_id+";";
        try (Connection connection = dataSource.getConnection()){
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(qur);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sasd);
            statement2.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.user_chat_id = user_chat_id;
    }

    @Override
    public List<String> getGroup() {
        dataSource.setPoolProperties(poolDateBase.getP());
        String pois = "select (id_group) from telegrambot_chat_id;";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                group_id.add(resultSet.getString("id_group"));
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return group_id;
    }

    @Override
    public void setGroup_id(String group_id) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String qur = "with x as (select "+group_id+" as id_group)\n" +
                "insert into telegrambot_chat_id (id_group) " +
                "(select * from x where not exists(select * from telegrambot_chat_id c where x.id_group=c.id_group ));";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getUser_group() {
        dataSource.setPoolProperties(poolDateBase.getP());
        String pois = "select * from group_id where (chat_id = "+user_chat_id+" and user_group = '"+userGroup+"' );";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                userGroup = resultSet.getString("user_group");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userGroup;
    }

    @Override
    public void setUser_group(String chat_id, String user_group) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String qur = "with x as (select "+chat_id+"  as chat_id , "+user_group+" as user_group)\n" +
                "insert into group_id (chat_id,user_group)" +
                "(select * from x where not exists(select * from group_id t where x.chat_id = t.chat_id));";
        String sasd = "UPDATE group_id \n" +
                "set user_group = '"+user_group+"'\n" +
                "where chat_id  = '"+chat_id+"';";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(sasd);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.user_chat_id = chat_id;
        this.userGroup = user_group;
    }

    @Override
    public String getUser_text() {
        dataSource.setPoolProperties(poolDateBase.getP());
        String search = "select (user_text) from text_id ti where (chat_id = "+user_chat_id+");";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(search);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()){
                user_text = resultSet.getString("user_text");
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user_text;
    }

    @Override
    public void setUser_text(String user_chat_id,String text_user) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String qur = "with x as (select "+user_chat_id+" as chat_id , '"+text_user+"' as user_text)\n" +
                "insert into text_id (chat_id,user_text)(select * from x where not exists(select * from text_id c where x.chat_id=c.chat_id));";
        String sasd = "UPDATE text_id \n" +
                "set user_text = '"+text_user+"'\n" +
                "where chat_id  = '"+user_chat_id+"';";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(sasd);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.user_chat_id = user_chat_id;
        this.user_text = text_user;
    }

   /* public String getIdThread(String user_chat_id) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String pois = "select * from telegram_thread where id_user = "+user_chat_id+" ;";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(pois);
            ResultSet resultSet = statement1.executeQuery();
            if (!resultSet.next()){
                user_chat_id = null;
            }
            while (resultSet.next()){
                idThread = (resultSet.getString("id_user"));
            }
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user_chat_id;
    }

    public void setIdThread(String user_chat_id,String nameThread) {
        dataSource.setPoolProperties(poolDateBase.getP());
        String qur = "with x as (select "+user_chat_id+" as id_user , '"+nameThread+"' as user_thread)\n" +
                "insert into telegram_thread (id_user,user_thread)(select * from x where not exists(select * from telegram_thread c where x.id_user=c.id_user));";
        String sasd = "UPDATE telegram_thread \n" +
                "set user_thread = '"+nameThread+"'\n" +
                "where id_user  = '"+user_chat_id+"';";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement2 = connection.prepareStatement(qur);
            statement2.executeUpdate();
            PreparedStatement statement = connection.prepareStatement(sasd);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.user_chat_id = user_chat_id;
        this.user_text = nameThread;
    }*/
}
