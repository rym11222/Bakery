package com.example.demoBot.service;

public class Dialogue {
    static String startDialogue(String massage){
        switch (massage) {
            case ("/start"):
                return Bakery.help();
            case ("/help"):
                return Bakery.help();
            case ("/menu"):
                return Bakery.menu();
        
            default:
                return "not recognized";
        }
    }

}
