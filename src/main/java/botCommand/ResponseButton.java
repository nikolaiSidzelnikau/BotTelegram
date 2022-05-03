package botCommand;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.TimeUnit;

public class ResponseButton extends BotCommand{
    CallbackQuery callbackQuery;

    @Override
    public void onUpdateReceived(Update update) {
        super.onUpdateReceived(update);
        if (update.hasCallbackQuery()){
            getCallbackQuery(update);
        }
    }

    public void getCallbackQuery(Update update) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        switch (data) {
            case "1":
                try {
                    execute(botCommand.sendMessage
                            (jdbcPostgreSQLExample.getId(), "Выберите куда отправлять", "Група",
                                    "3", "Чат", "4"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                try {
                    execute(botCommand.sendMessage(jdbcPostgreSQLExample.getId(), "Введите текст отправить текст Enter и нажмите кнопку ОК","OK","6"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "5":
                break;
            case "6":
                try {
                    execute(botCommand.sendMessage(jdbcPostgreSQLExample.getId(), "Этот текст вы хотите пересылать ? \n"+ getTextButton(),
                            "OK","8","НЕТ","4"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "7":
                jdbcPostgreSQLExample.setRecord(jdbcPostgreSQLExample.getId(), String.valueOf(getTextButton()));
                /*try {
                    execute(botCommand.sendMessage(getGroupChat_id(),"Введите ссылку на свой чат отправить Enter и нажмите кнопку OK",
                            "Ok","8","Нет","7"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }*/
                while (true) {
                    try {
                        execute(botCommand.sendMessage
                                (getGroupChat_id(), "@" + getBotUsername() +
                                        "\n" + "@" + getNameUser() + "\n" +
                                        jdbcPostgreSQLExample.getText(getId_User())));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "8":
                try {
                    execute(botCommand.sendMessage(getGroupChat_id(),"Привет"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
