package bot;

import database.JDBSDateBase;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    private String BOT_NAME = "";
    private String BOT_TOKEN = "";
    public JDBSDateBase jdbsDateBase = new JDBSDateBase();

    @Override
    public void onUpdateReceived( Update update) {
        if (update.hasMessage()){
            jdbsDateBase.setChat_id(String.valueOf(update.getMessage().getFrom().getId()),
                    (update.getMessage().getFrom().getUserName()));
            jdbsDateBase.setTextUserBot(String.valueOf(update.getMessage().getFrom().getId()),
                    update.getMessage().getText());
            if (update.getMessage().getChatId()<0) {
                jdbsDateBase.setUser_group(String.valueOf(update.getMessage().getFrom().getId()),
                        String.valueOf((update.getMessage().getChatId())));
            }
        }
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

