import bot.TestBot;
import botCommand.BotCommand;
import botCommand.ResponseButton;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Run {
    public static void main(String[] args) throws TelegramApiException {
        TestBot bot = new ResponseButton();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}
