package botCommand;

import SendMesseng.TestBotCommand;
import bot.TestBot;
import database.JDBCPostgreSQLExample;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCommand extends TestBot {
    JDBCPostgreSQLExample jdbcPostgreSQLExample = new JDBCPostgreSQLExample();
    TestBotCommand botCommand = new TestBotCommand();

    @Override
    public void onUpdateReceived(Update update) {
        super.onUpdateReceived(update);
        if (update.hasMessage()){
            commandStart(update);
            commandStartBot(update);
            commandHelp(update);
        }
    }

    public void commandStart(Update update) {
        if (update.getMessage().getText().equals("/start")) {
            setId_User(String.valueOf(update.getMessage().getFrom().getId()));
            setNameUser(update.getMessage().getFrom().getUserName());
            jdbcPostgreSQLExample.getRecord(getId_User(), getNameUser());
            try {
                execute(botCommand.sendMessage
                        (getUserChat_id(),
                                "Добро пожаловать перейдите по ссылке для" +
                                        " дольнейшей работы и напишите команду "+"@"+getBotUsername()+"  /startbot"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void commandStartBot(Update update){
        if (update.getMessage().getText().equals("/startbot")){
            try {
                execute(botCommand.sendMessage(jdbcPostgreSQLExample.getId(),"Добро пожаловать",
                        "Начать работу с ботом","1",
                        "Как работать с ботом","2"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void commandHelp(Update update){
        if (update.getMessage().getText().equals("/help")){

        }
    }
}
