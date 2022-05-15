package bot;

import ButtonCallback.ButtonsCallbacks;
import botCommand.BotCommandStart;
import botCommand.BotCommandStartBot;
import botCommand.BotCommands;
import database.DateBaseSearch;
import database.JDBSDateBaseSearch;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

public class Bot extends TelegramLongPollingBot {
    private String getId;
    BotCommands botCommands1;
    BotCommands botCommands2;
    ButtonsCallbacks buttonsCallbacks = new ButtonsCallbacks();
    JDBSDateBaseSearch jdbsDateBase = new DateBaseSearch();

    @Override
    public void onUpdateReceived( Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().getText().equals("/start") && update.getMessage().getChatId() < 0) {
                jdbsDateBase.setUser_group(String.valueOf(update.getMessage().getFrom().getId()),
                        String.valueOf(update.getMessage().getChatId()));
                jdbsDateBase.setGroup_id(String.valueOf(update.getMessage().getChatId()));
                botCommands1 = new BotCommandStart();
                botCommands1.command(jdbsDateBase.getUser_group());
            }else if (update.getMessage().getText().equals("/start") && update.getMessage().getChatId() > 0){
                botCommands1 = new BotCommandStart();
                botCommands1.commandFalse(String.valueOf(update.getMessage().getFrom().getId()));
            }
            if (update.getMessage().getText().equals("/startbot") && update.getMessage().getChatId() > 0) {
                jdbsDateBase.setUser_chat_id(String.valueOf(update.getMessage().getFrom().getId()),
                        update.getMessage().getFrom().getUserName());
                botCommands2 = new BotCommandStartBot();
                botCommands2.command(jdbsDateBase.getUser_chat_id());
            }else if (update.getMessage().getText().equals("/startbot") && update.getMessage().getChatId() < 0){
                botCommands2 = new BotCommandStartBot();
                botCommands2.commandFalse(String.valueOf(update.getMessage().getChatId()));
            }
            if (jdbsDateBase.getUser_chat_id(String.valueOf(update.getMessage().getFrom().getId())) != null) {
                jdbsDateBase.setUser_text(String.valueOf(update.getMessage().getFrom().getId()),
                        update.getMessage().getText());
            }
        }else if (update.hasCallbackQuery()){
            String s = String.valueOf(update.getCallbackQuery().getFrom().getId());
            if (!Objects.equals(getGetId(), s) && getGetId() != null){
                jdbsDateBase.setUser_chat_id(s,update.getCallbackQuery().getFrom().getUserName());
            }
            setGetId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
            buttonsCallbacks.getUpdateHasCallbackQuery(update,jdbsDateBase.getUser_chat_id(),jdbsDateBase.getUser_group(),
                    jdbsDateBase.getName_user(),getBotUsername(), jdbsDateBase.getUser_text());
        }
    }

    public String getGetId() {
        return getId;
    }

    public void setGetId(String getId) {
        this.getId = getId;
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

}

