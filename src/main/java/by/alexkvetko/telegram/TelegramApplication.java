package by.alexkvetko.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
public class TelegramApplication implements CommandLineRunner {

	@Autowired
	TelegramLongPollingBot longPollingBot;

	{
		ApiContextInitializer.init();
	}
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TelegramApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... strings) throws Exception {

		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(longPollingBot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
