package botCommand;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCommandStart implements BotCommands{
    Bot bot = new Bot();
    BotCommandSend botCommand = new BotCommandSend();

    @Override
    public void command(Update update,String chat_id) {
        if (update.getMessage().getText().equals("/start")) {
            try {
                bot.execute(botCommand.sendMessage
                        (chat_id,
                                "Добро пожаловать перейдите по ссылке для" +
                                        " дольнейшей работы и напишите команду "+"@"+bot.getBotUsername()+"  /startbot"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
