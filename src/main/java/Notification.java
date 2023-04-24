import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

public class Notification extends TelegramLongPollingBot {

    private static final String botToken = "6168704335:AAGG-ZO__MaurMWMsFaVCsFHrUs9zZmVqqk";
    private static final String botName = "EarlyNotificationbot";
    private String chatId;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
        }
    }

    public void sendMessage(String[] phrases) {
        if (chatId != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            Random random = new Random();
            int index = random.nextInt(phrases.length);
            String text = phrases[index];

            sendMessage.setText(text);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Chat id is NULL");
        }

    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

