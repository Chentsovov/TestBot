import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Bot extends TelegramLongPollingBot {
    //создаем две константы, присваиваем им значения токена и имя бота соответсвтенно
    //вместо звездочек подставляйте свои данные
    final private String BOT_TOKEN = "5461059340:AAGyXdRdTeVEdo9sdUy6wJRIPbvQlDSXwOk";
    final private String BOT_NAME = "@Axelrazenklein_bot";
    Storage storage;

    Bot()
    {
        storage = new Storage();
    }
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//инициализация кнопок
    public void initKeyboard()
    {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом в ряд

        keyboardRow.add(new KeyboardButton("текст первой кнопки"));
        keyboardRow.add(new KeyboardButton("текст второй кнопки"));
        keyboardRow.add(new KeyboardButton("текст третей кнопки"));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }


//и это инициализация кнопок тоже
//        public synchronized void setButtons(SendMessage sendMessage) {
//
//            // Create a keyboard
//            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//            sendMessage.setReplyMarkup(replyKeyboardMarkup);
//            replyKeyboardMarkup.setSelective(true);
//            replyKeyboardMarkup.setResizeKeyboard(true);
//            replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//            // Create a list of keyboard rows
//            List keyboard = new ArrayList<>();
//        // First keyboard row
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//
//        // Add buttons to the first keyboard row
//        keyboardFirstRow.add(new KeyboardButton("Hi"));
//
//        // Second keyboard row
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//        // Add the buttons to the second keyboard row
//        keyboardSecondRow.add(new KeyboardButton("Help"));
//
//        // Add all of the keyboard rows to the list
//        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
//        // and assign this list to our keyboard
//        replyKeyboardMarkup.setKeyboard(keyboard);
//    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ

                outMess.setChatId(chatId);
                outMess.setText(response);
//                initKeyboard();
//                outMess.setReplyMarkup(replyKeyboardMarkup);
//вот тут косяк , не инициализируется метод
                //Отправка в чат

                execute(outMess);
                //outMess.setReplyMarkup(replyKeyboardMarkup);

            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public String parseMessage(String textMsg) {
        String response;

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if(textMsg.equals("/start"))
            response = "тестовый бот у которого не работают кнопки :Е";

        else if(textMsg.equals("/get") || textMsg.equals("текст первой кнопки"))
            response = storage.getRandQuote();
//        else if(textMsg.equals("/get"))
//            response = storage.getRandQuote();
        else
            response = "Сообщение не распознано";

        return response;
    }

}