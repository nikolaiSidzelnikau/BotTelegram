package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import database.DateBaseSearch;
import database.JDBSDateBaseSearch;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.TimeUnit;

public class ButtonsCallback8 implements ButtonsCallback,Runnable{
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    JDBSDateBaseSearch jdbsDateBase = new DateBaseSearch();


    private final String text;
    private final String name;
    private final String nameBot;

    public ButtonsCallback8( String text, String name, String nameBot) {

        this.text = text;
        this.name = name;
        this.nameBot = nameBot;
    }

    @Override
    public Update getCallbackQuery(Update update, String chat_id) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("8")) {
            if (!update.getCallbackQuery().getFrom().getId().equals(update.getCallbackQuery().getFrom().getId())) {
                System.out.println("Второй поток");
                Thread thread = new Thread(new ButtonsCallback8( text, name, nameBot));
                thread.start();
            }
            Thread thread1 = new Thread(new ButtonsCallback8( text, name, nameBot));
            thread1.start();
        }
        return update;
    }

    @Override
    public void run() {
        for (int s = 0;s<1000;s++) {
            for (String s1: jdbsDateBase.getGroup()) {
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
