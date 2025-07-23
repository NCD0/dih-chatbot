package org.example.demo1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public class Controller {
    @FXML private Button sendPrompt;
    @FXML private TextField prompt;
    @FXML private ScrollPane scroller;
    @FXML private VBox box;

    public void SendPrompt() {
        sendPrompt.setOnAction(e -> {
            String promptText = prompt.getText();

            if (promptText.isEmpty()) {
                return;
            }

            addUserBubble(promptText);
            System.out.println("sending prompt: " + promptText);
            prompt.setText("");

            new Thread(() -> {
                SendApiRequest sendApiRequest = new SendApiRequest();
                ObjectMapper mapper = new ObjectMapper();
                String jsonHistory = new Gson().toJson(ChatHistory.chatHistory);
                Body text = new Body(promptText, jsonHistory);
                try {
                    HttpResponse<String> response = sendApiRequest.sendApiRequest(text);
                    String answer = response.body();
                    JsonNode rootNode = mapper.readTree(answer);
                    String msg = rootNode.get("msg").asText();

                    ChatHistory.chatHistory.add(new ChatHistory.Message("user", List.of(new ChatHistory.Part(promptText))));
                    ChatHistory.chatHistory.add(new ChatHistory.Message("model", List.of(new ChatHistory.Part(msg))));

                    javafx.application.Platform.runLater(() -> {
                        addBotBubble(msg);
                    });
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });
    }

    private void addBotBubble(String botText) {
        Text text = new Text(botText);
        text.setStyle("-fx-fill: white;");
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(300);
        textFlow.setStyle("-fx-background-color: #2c2c2c; -fx-padding: 10 15 10 15; -fx-background-radius: 15;");

        HBox bubble = new HBox(textFlow);
        bubble.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT;");

        box.getChildren().add(bubble);

        scroller.layout();
        scroller.setVvalue(1.0);
    }

    private void addUserBubble(String userText) {
        Text text = new Text(userText);
        text.setStyle("-fx-fill: white;");
        TextFlow textFlow = new TextFlow(text);
        textFlow.setMaxWidth(300);
        textFlow.setStyle("-fx-background-color: #004999; -fx-padding: 10 15 10 15; -fx-background-radius: 15;");

        HBox bubble = new HBox(textFlow);
        bubble.setStyle("-fx-padding: 10; -fx-alignment: CENTER_RIGHT;");

        box.getChildren().add(bubble);

        scroller.layout();
        scroller.setVvalue(1.0);
    }
}