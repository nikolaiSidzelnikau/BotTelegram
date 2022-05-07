package ButtonCallback;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ButtonsCallback1  implements ButtonsCallback {
    CallbackQuery callbackQuery;
    Bot bot ;

    @Override
    public void getCallbackQuery(Update update,String chat_id,String text) {
        bot = new Bot();
        BotCommandSend botCommandSend = new BotCommandSend();
        callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        if (data.equals("1")){
            try {
                bot.execute(botCommandSend.sendMessage
                        (chat_id, "Выберите куда отправлять", "Група",
                                "3", "Чат", "4"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
