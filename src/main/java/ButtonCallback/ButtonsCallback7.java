package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.TimeUnit;

public class ButtonsCallback7 implements ButtonsCallback,Runnable{
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    private String chat_id;
    private String text;
    private String name;
    private String nameBot;

    public ButtonsCallback7(String chat_id, String text,String name,String nameBot) {
        this.chat_id = chat_id;
        this.text = text;
        this.name = name;
        this.nameBot = nameBot;
    }

    @Override
    public void getCallbackQuery(Update update,String chat_id,String text) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("7")) {
            Thread thread = new Thread(new ButtonsCallback7(chat_id,text,name,nameBot));
            thread.start();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                bot.execute(botCommand.sendMessage(chat_id,"@"+nameBot+"\n"+"@"+name+"\n"+text));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
