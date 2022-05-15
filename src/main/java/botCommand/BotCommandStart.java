package botCommand;

import SendMesseng.BotCommandSend;
import bot.Bot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCommandStart implements BotCommands {
    Bot bot = new Bot();
    BotCommandSend botCommand = new BotCommandSend();

    @Override
    public void command(String chat_id) {
        try {
            bot.execute(botCommand.sendMessage
                    (chat_id,
                            "Добро пожаловать перейдите по ссылке для" +
                                    " дольнейшей работы и напишите команду " + "@" + bot.getBotUsername() + "  /startbot"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commandFalse(String chat_id){
        try {
            bot.execute(botCommand.sendMessage
                    (chat_id, "Уважаемый пользователь перейдите в группу в которой находится бот и напишите /start"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
