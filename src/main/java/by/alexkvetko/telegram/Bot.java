package by.alexkvetko.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

  @Value("${token}")
  private String token;

  public void onUpdateReceived(Update update) {

    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      // Set variables
      String message_text = update.getMessage().getText();
      long chat_id = update.getMessage().getChatId();
      String messageToSend = new StringBuffer(message_text).reverse().toString();

      SendMessage message = new SendMessage() // Create a message object object
          .setChatId(chat_id)
          .setText(messageToSend);
      try {
        execute(message); // Sending our message object to user
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  public String getBotUsername() {
    return "xela_bot";
  }

  @Override
  public String getBotToken() {
    return token;
  }

}
