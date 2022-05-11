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
    private final String chat_id;
    private final String text;
    private final String name;
    private final String nameBot;

    public ButtonsCallback7(String chat_id, String text, String name, String nameBot) {
        this.chat_id = chat_id;
        this.text = text;
        this.name = name;
        this.nameBot = nameBot;
    }

    @Override
    public void getCallbackQuery(Update update, String chat_id, String text) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("7")) {
            if (!update.getCallbackQuery().getFrom().getId().equals(update.getCallbackQuery().getFrom().getId())) {
                System.out.println("Второй поток");
                Thread thread = new Thread(new ButtonsCallback7(chat_id, text, name, nameBot));
                thread.start();
            }
            Thread thread1 = new Thread(new ButtonsCallback7(chat_id, text, name, nameBot));
            thread1.start();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                bot.execute(botCommand.sendMessage(chat_id, "@" + nameBot + "\n" + "@" + name + "\n" + text));
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
