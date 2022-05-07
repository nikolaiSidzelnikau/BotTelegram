package botCommand;

import org.telegram.telegrambots.meta.api.objects.Update;

public class BotCommandHelp implements BotCommands{

    @Override
    public void command(Update update,String chat_id) {
        if (update.getMessage().getText().equals("/help")){

        }
    }
}
