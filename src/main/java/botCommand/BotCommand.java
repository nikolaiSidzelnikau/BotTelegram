package botCommand;

import ButtonCallback.*;
import bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotCommand extends Bot {

    @Override
    public void onUpdateReceived(Update update) {
        super.onUpdateReceived(update);
        if (update.hasMessage()) {
            BotCommands botCommands1 = new BotCommandStart();
            botCommands1.command(update,jdbsDateBase.getUser_group());
            BotCommands botCommands2 = new BotCommandStartBot();
            botCommands2.command(update,jdbsDateBase.getChat_id());
            BotCommands botCommands3 = new BotCommandHelp();
            botCommands3.command(update,jdbsDateBase.getChat_id());
        }else  if (update.hasCallbackQuery()){
            ButtonsCallback buttonsCallback1 = new ButtonsCallback1();
            buttonsCallback1.getCallbackQuery(update,jdbsDateBase.getChat_id(), jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback2 = new ButtonsCallback2();
            buttonsCallback2.getCallbackQuery(update,jdbsDateBase.getChat_id(),jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback3 = new ButtonsCallback3();
            buttonsCallback3.getCallbackQuery(update,jdbsDateBase.getChat_id(),jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback4 = new ButtonsCallback4();
            buttonsCallback4.getCallbackQuery(update,jdbsDateBase.getChat_id(),jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback5 = new ButtonsCallback5();
            buttonsCallback5.getCallbackQuery(update,jdbsDateBase.getChat_id(),jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback6 = new ButtonsCallback6();
            buttonsCallback6.getCallbackQuery(update,jdbsDateBase.getChat_id(),jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback7= new ButtonsCallback7(jdbsDateBase.getChat_id(),jdbsDateBase.getText_user(),
                    jdbsDateBase.getUser_name(),getBotUsername());
            buttonsCallback7.getCallbackQuery(update,jdbsDateBase.getUser_group(),jdbsDateBase.getText_user());
            ButtonsCallback buttonsCallback8 = new ButtonsCallback8();
            buttonsCallback8.getCallbackQuery(update,jdbsDateBase.getChat_id(),jdbsDateBase.getText_user());
        }
    }
}
