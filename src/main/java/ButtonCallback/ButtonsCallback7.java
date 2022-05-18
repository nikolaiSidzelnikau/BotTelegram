package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.concurrent.TimeUnit;

public class ButtonsCallback7 implements ButtonsCallback, Runnable {
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    private final String id_chat;
    private final String id_group;
    private final String text;
    private final String nameBot;
    public ButtonsCallback7(String id_chat, String chat_id, String text, String nameBot) {
        this.id_chat = id_chat;
        this.id_group = chat_id;
        this.text = text;
        this.nameBot = nameBot;
    }

    @Override
    public void getCallbackQuery(Update update, String chat_id) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("7")) {
            Thread thread1 = new Thread(new ButtonsCallback7(id_chat, chat_id, text, nameBot));
            thread1.start();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                bot.execute(botCommand.sendMessage(id_group, "@" + nameBot +"\n" + text));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
