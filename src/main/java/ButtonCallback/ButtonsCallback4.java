package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ButtonsCallback4 implements ButtonsCallback{
    CallbackQuery callbackQuery;
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();
    @Override
    public Update getCallbackQuery(Update update, String chat_id) {
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("4")){
            try {
                bot.execute(botCommand.sendMessage(chat_id,
                        "Введите текст отправить текст Enter и нажмите кнопку ОК","OK","6"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return update;
    }
}
