package ButtonCallback;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ButtonsCallback {
    Update getCallbackQuery(Update update, String chat_id);
}
