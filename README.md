# Dih Chatbot

A simple chatbot desktop app built with **JavaFX** and connected to a **Node.js Express server**. It uses the **Google Gemini API** to generate responses based on your chat history.
Please keep in mind this might not be a great resource since I built that app with like only 1 week of learning Java.

How it works is basically the JavaFx client provides a simple chat UI and when u send a message it sends a `POST` request to the Node.js server and that request takes in two parameters which are the message and your chat history, then the server creates a new gemini chat and sends it all to the gemini api and after all that it returns the response back to the java app.
I added google search so that the api would do google search when told to or when needed but you can modify it as you like.

I made it obsessed with Dih just because im bored

## How to setup:
**JavaFX Client**
1. Clone the repo:
```bash
git clone https://github.com/NCD0/dih-chatbot.git
cd dih-chatbot
```
2. Open in IntelliJ IDEA or your preferred IDE.
3. Build and run `Main.java`.

**Express Server**
1. Navigate to `server.js`.
2. Create package.json since I didn't add it in the repo and then install dependencies
```bash
npm init -y
```
```bash
npm i express @google/genai
```
3. Open `server.js` and paste in your api key (you can get it from google ai studio)
4. Start the server:
```bash
node server.js
```

Please keep in mind that I made this app for learning purposes only and that if you were to release it, it'd be better to save the chat history somewhere like a database and secure senstive things like api keys somewhere else like `.env` files. Also don't forget to implement things like ratelimiting.

<img width="803" height="676" alt="image" src="https://github.com/user-attachments/assets/1520e8b1-332f-465d-b6a8-e463c8bb431d" />
