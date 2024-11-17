package com.example.demoBot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.demoBot.config.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot{

    BotConfig config;

    public TelegramBot(BotConfig config){
        this.config = config;
    }

    @Override
    public String getBotUsername(){
        return config.getBotName();
    }

    @Override 
    public String getBotToken(){
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update){

        if (update.hasMessage() && update.getMessage().hasText()){
            String massageText = update.getMessage().getText();

            long chatId = update.getMessage().getChatId();

            sendMessage(chatId, Dialogue.startDialogue(massageText));

            /*switch (massageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Not recognized");
            }*/
        } 

    }
    /*private void startCommandReceived(long chatId, String name){
        String answer = "Hi, " + name + ", nice to meet you";



        sendMessage(chatId, answer);
    }*/

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e){

        }

    }

}
