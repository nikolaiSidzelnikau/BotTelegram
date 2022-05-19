package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import database.DataBaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ButtonsCallback8 implements ButtonsCallback,Runnable{
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private int group_id = 0;
    private final String text;
    private final String nameBot;
    List<String> group = new ArrayList<>();
    final static Logger logger = LoggerFactory.getLogger(ButtonsCallback8.class);

    public ButtonsCallback8(String text, String nameBot, List<String> group) {
        this.text = text;
        this.nameBot = nameBot;
        this.group = group;
    }

    public ButtonsCallback8(String text, String nameBot) {
        this.text = text;
        this.nameBot = nameBot;
    }

    @Override
    public void getCallbackQuery(Update update, String chat_id) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("8")) {
            dataBaseConnection.getConnections("","",5);
            for (String s1: dataBaseConnection.getGroup()) {
                group.add(s1);
            }
            Thread thread1 = new Thread(new ButtonsCallback8(text, nameBot,group));
            thread1.start();
        }
    }

    @Override
    public void run() {
        for (int s = 0;s<1000;s++) {
            for (String s1: group) {
                    try {
                        bot.execute(botCommand.sendMessage(s1,
                                "@" + nameBot + "\n" + text));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
