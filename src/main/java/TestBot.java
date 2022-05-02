
import SendMesseng.TestBotCommand;
import database.JDBCPostgreSQLExample;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestBot extends TelegramLongPollingBot {
    private String BOT_NAME = "";
    private String BOT_TOKEN = "";
    private String chat_id;
    private String nameUser;
    private String id_User;
    private Message inlineButton;
    private Update update;
    TestBotCommand botCommand = new TestBotCommand();
    JDBCPostgreSQLExample jdbcPostgreSQLExample = new JDBCPostgreSQLExample();
    CallbackQuery callbackQuery;
    List<String> textButton = new ArrayList<>();


    @Override
    public void onUpdateReceived( Update update) {
         callbackQuery = update.getCallbackQuery();
        if (update.hasMessage()){
            setTextButton(update.getMessage().getText());
            setChat_id(String.valueOf(update.getMessage().getChatId()));
           isEqualsStart(update);
          // getText(update);
        }else if (update.hasCallbackQuery()){
            getCallbackQuery(update);
        }

    }

    public void isEqualsStart( Update update) {
        this.update = update;
        if (update.getMessage().getText().equals("/start")) {
            setId_User(String.valueOf(update.getMessage().getFrom().getId()));
            setNameUser(update.getMessage().getFrom().getUserName());
            jdbcPostgreSQLExample.getRecord(getId_User(), getNameUser());
            try {
                execute(botCommand.sendMessage
                        (getChat_id(), "Добро пожаловать", "Начать работу с ботом", "1",
                                "Как пользоваться ботом", "5"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCallbackQuery(Update update) {
        String data = callbackQuery.getData();
        switch (data) {
            case "1":
                try {
                    execute(botCommand.sendMessage
                            (getChat_id(), "Выберите куда отправлять", "Група",
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
                    execute(botCommand.sendMessage(getChat_id(),"Введите текст отправить текст Enter и нажмите кнопку ОК","OK","6"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "5":
                break;
            case "6":
                try {
                    execute(botCommand.sendMessage(getChat_id(),"Этот текст вы хотите пересылать ? \n"+ getTextButton(),
                            "OK","7","НЕТ","4"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "7":
                jdbcPostgreSQLExample.setRecord(getId_User(), String.valueOf(getTextButton()));
                while (true) {
                    try {
                        execute(botCommand.sendMessage
                                (getChat_id(), "@" + getBotUsername() +
                                        "\n" + "@" + getNameUser() + "\n" +
                                        jdbcPostgreSQLExample.getText(getId_User())));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               /* break;*/
        }
    }

    public List<String> getTextButton() {
        return textButton;
    }

    public void setTextButton(String textButton) {
        this.textButton = Collections.singletonList(textButton);
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
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

