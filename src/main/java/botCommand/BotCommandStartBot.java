package botCommand;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCommandStartBot implements BotCommands{
    BotCommandSend botCommand = new BotCommandSend();
    Bot bot = new Bot();

    @Override
    public void command(String chat_id) {
            try {
                bot.execute(botCommand.sendMessage(chat_id,
                        "Добро пожаловать",
                        "Начать работу с ботом", "1",
                        "Как работать с ботом", "2"));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void commandFalse(String chat_id){
        try {
            bot.execute(botCommand.sendMessage
                    (chat_id, "Уважаемый пользователь перейдите в бот чат и напишите /startbot"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
