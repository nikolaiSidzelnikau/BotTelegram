package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import database.DataBaseGroup_id;
import database.DateBaseCommands;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.TimeUnit;

public class ButtonsCallback8 implements ButtonsCallback,Runnable{
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    DateBaseCommands dateBaseCommands = new DataBaseGroup_id();
    DataBaseGroup_id dataBaseGroup_id = new DataBaseGroup_id();

    private final String text;
    private final String nameBot;
    public ButtonsCallback8( String text, String nameBot) {
        this.text = text;
        this.nameBot = nameBot;
    }

    @Override
    public void getCallbackQuery(Update update, String chat_id) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("8")) {
            Thread thread1 = new Thread(new ButtonsCallback8( text, nameBot));
            thread1.start();
        }
    }

    @Override
    public void run() {
        for (int s = 0;s<1000;s++) {
                for (String s1:dataBaseGroup_id.group_id ) {
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
