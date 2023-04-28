import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DecimalFormat;
import java.util.Calendar;
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

    private double calculatePercentageOfYear() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int dayOfYear = now.get(Calendar.DAY_OF_YEAR);
        int totalDaysInYear = now.getActualMaximum(Calendar.DAY_OF_YEAR);
        return ((double) dayOfYear / totalDaysInYear) * 100;
    }

    public void sendMessage() {
        if (chatId != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            double percentageOfYear = calculatePercentageOfYear();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String responseText = "У році пройшло " + decimalFormat.format(percentageOfYear) + "%";
            String text = responseText;
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

