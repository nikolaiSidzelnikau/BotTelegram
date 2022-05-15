package ButtonCallback;

import org.telegram.telegrambots.meta.api.objects.Update;

public class ButtonsCallbacks {


    public void getUpdateHasCallbackQuery(Update update, String id_chat, String id_group, String name, String nameBot, String text) {
        ButtonsCallback buttonsCallback1 = new ButtonsCallback1();
        buttonsCallback1.getCallbackQuery(update, id_chat);
        ButtonsCallback buttonsCallback2 = new ButtonsCallback2();
        buttonsCallback2.getCallbackQuery(update, id_chat);
        ButtonsCallback buttonsCallback3 = new ButtonsCallback3();
        buttonsCallback3.getCallbackQuery(update, id_chat);
        ButtonsCallback buttonsCallback4 = new ButtonsCallback4();
        buttonsCallback4.getCallbackQuery(update, id_chat);
        ButtonsCallback buttonsCallback5 = new ButtonsCallback5();
        buttonsCallback5.getCallbackQuery(update, id_chat);
        ButtonsCallback buttonsCallback6 = new ButtonsCallback6(text);
        buttonsCallback6.getCallbackQuery(update, id_chat);
        ButtonsCallback buttonsCallback7= new ButtonsCallback7(id_chat, id_group, text, name, nameBot);
        buttonsCallback7.getCallbackQuery(update, id_group);
        ButtonsCallback buttonsCallback8 = new ButtonsCallback8( text, name, nameBot);
        buttonsCallback8.getCallbackQuery(update, String.valueOf(id_group));
    }
}
