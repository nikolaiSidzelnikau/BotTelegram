package database;

import java.util.List;

public interface JDBSDateBaseSearch {
    String getName_user();

    void setName_user(String user_chat_id, String name_user);
    String getUser_chat_id(String user_chat_id);

    String getUser_chat_id();

    void setUser_chat_id(String user_chat_id, String name_user);

    List<String> getGroup();

    void setGroup_id(String group_id);

    String getUser_group();

    void setUser_group(String chat_id, String user_group);

    String getUser_text();

    void setUser_text(String user_chat_id, String text_user);
}
