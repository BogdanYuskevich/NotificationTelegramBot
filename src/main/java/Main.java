import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        String[] phrases = {"test1","tes2","test3","test4","test5"};
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Notification bot = new Notification();
            botsApi.registerBot(bot);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bot.sendMessage(phrases);
                }
            },0,60000);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
