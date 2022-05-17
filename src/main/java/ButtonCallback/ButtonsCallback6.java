package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ButtonsCallback6 implements ButtonsCallback{
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    private final String text;

    public ButtonsCallback6(String text) {
        this.text = text;
    }

    @Override
    public void getCallbackQuery(Update update, String chat_id) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("6")){
            try {
                bot.execute(botCommand.sendMessage(chat_id, "Этот текст вы хотите пересылать ? \n"+ getText(),
                        "OK","7","НЕТ","4"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getText() {
        return text;
    }

}
