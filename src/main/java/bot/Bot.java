package bot;

import ButtonCallback.ButtonsCallbacks;
import botCommand.BotCommandStart;
import botCommand.BotCommandStartBot;
import botCommand.BotCommands;
import database.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

public class Bot extends TelegramLongPollingBot {
    private String getId;
    BotCommands botCommands1;
    BotCommands botCommands2;
    ButtonsCallbacks buttonsCallbacks = new ButtonsCallbacks();
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    DESDecrypter desDecrypter = new DESDecrypter();
    final static Logger logger = LoggerFactory.getLogger(Bot.class);

    @Override
    public void onUpdateReceived( Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().getText().equals("/start") && update.getMessage().getChatId() < 0) {
                dataBaseConnection.getConnections(String.valueOf(update.getMessage().getFrom().getId()),
                        update.getMessage().getFrom().getUserName(),2);
                dataBaseConnection.getConnections(String.valueOf(update.getMessage().getFrom().getId()),
                        String.valueOf(update.getMessage().getChatId()),4);
                dataBaseConnection.getConnections(String.valueOf(update.getMessage().getFrom().getId()),
                        String.valueOf(update.getMessage().getChatId()),6);
                botCommands1 = new BotCommandStart();
                botCommands1.command(dataBaseConnection.getConnections("","",3));
            }else if (update.getMessage().getText().equals("/start") && update.getMessage().getChatId() > 0){
                botCommands1 = new BotCommandStart();
                botCommands1.commandFalse(String.valueOf(update.getMessage().getFrom().getId()));
            }

            if (update.getMessage().getText().equals("/startbot") && update.getMessage().getChatId() > 0) {
                dataBaseConnection.getConnections(String.valueOf(update.getMessage().getFrom().getId()),
                        update.getMessage().getFrom().getUserName(),2);
                botCommands2 = new BotCommandStartBot();
                botCommands2.command(dataBaseConnection.getConnections("","",9));
            }else if (update.getMessage().getText().equals("/startbot") && update.getMessage().getChatId() < 0){
                botCommands2 = new BotCommandStartBot();
                botCommands2.commandFalse(String.valueOf(update.getMessage().getChatId()));
            }

            if (dataBaseConnection.getConnections(String.valueOf(update.getMessage().getFrom().getId()),"",1) != null) {
                dataBaseConnection.getConnections(String.valueOf(update.getMessage().getFrom().getId()),
                        update.getMessage().getText(),8);
            }

        }else if (update.hasCallbackQuery()){
            String s = String.valueOf(update.getCallbackQuery().getFrom().getId());
            if (!Objects.equals(getGetId(), s) && getGetId() != null){
                dataBaseConnection.getConnections(s,update.getCallbackQuery().getFrom().getUserName(),2);
            }
            setGetId(String.valueOf(update.getCallbackQuery().getFrom().getId()));
            buttonsCallbacks.getUpdateHasCallbackQuery(update,dataBaseConnection.getConnections("", "",9),
                    dataBaseConnection.getConnections("","",3),
                    getBotUsername(), dataBaseConnection.getConnections(String.valueOf(update.getCallbackQuery().getFrom().getId()),"",7));
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
        return desDecrypter.decrypt("Thikdkfejfn;laenfjlkijesabflAL",
                "IKhHt2EpyysFPXZOBv8mT1LhgvAhoh8v");
    }

    @Override
    public String getBotToken() {
        return desDecrypter.decrypt("Thikdkfejfn;laenfjlkijesabflAL",
                "VGecy2Dy3W90hY1f8n20/FV6TDcunntTa4j0lgJiqtcORuZJRaOVM+61A2+8Mfon");
    }

}

