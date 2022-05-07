package botCommand;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCommandStartBot implements BotCommands{
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();

    @Override
    public void command(Update update,String chat_id) {
        if (update.getMessage().getText().equals("/startbot")){
            try {
                bot.execute(botCommand.sendMessage(chat_id,
                        "Добро пожаловать",
                        "Начать работу с ботом", "1",
                        "Как работать с ботом", "2"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
