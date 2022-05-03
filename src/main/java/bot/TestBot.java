package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestBot extends TelegramLongPollingBot {
    private String BOT_NAME = "";
    private String BOT_TOKEN = "";
    private String groupChat_id;
    private String userChat_id;
    private String nameUser;
    private String id_User;

    List<String> textButton = new ArrayList<>();



    @Override
    public void onUpdateReceived( Update update) {
        if (update.hasMessage()){
            setTextButton(update.getMessage().getText());
            setUserChat_id(String.valueOf(update.getMessage().getChatId()));
            if (update.getMessage().getChatId()<0) {
                setGroupChat_id(String.valueOf(update.getMessage().getChatId()));
            }
        }
    }

    public String getGroupChat_id() {
        return groupChat_id;
    }

    public void setGroupChat_id(String groupChat_id) {
        this.groupChat_id = groupChat_id;
    }

    public List<String> getTextButton() {
        return textButton;
    }

    public void setTextButton(String textButton) {
        this.textButton = Collections.singletonList(textButton);
    }

    public String getUserChat_id() {
        return userChat_id;
    }

    public void setUserChat_id(String userChat_id) {
        this.userChat_id = userChat_id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}

