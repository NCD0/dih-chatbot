package org.example.demo1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class ChatHistory {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static final List<Message> chatHistory = new java.util.ArrayList<>();

    public static void main(String[] args) {
        chatHistory.add(new Message("user", Arrays.asList(new Part("Hello"))));
        chatHistory.add(new Message("model", Arrays.asList(new Part("Hi, Dih is the best."))));

        String json = gson.toJson(chatHistory);
        System.out.println(json);
    }

    public static class Part {
        public String text;
        public Part(String text) { this.text = text; }
    }

    public static class Message {
        public String role;
        public List<Part> parts;
        public Message(String role, List<Part> parts) {
            this.role = role;
            this.parts = parts;
        }
    }
}