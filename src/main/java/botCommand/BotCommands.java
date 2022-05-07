package botCommand;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommands {
    void command(Update update,String chat_id);
}
