// this isn't made for production, don't share api keys and its better to store them somewhere like dotenv
const express = require('express');
const { GoogleGenAI } = require("@google/genai");
// npm i express @google/genai

const key = "" // api key from google ai studio

const ai = new GoogleGenAI({apiKey: key})

const app = express();
app.use(express.json())

app.post('/prompt', async (req, res) => {
    const { prompt, history } = req.body;

    if (!prompt) return res.status(400).json({ msg: "Missing prompt" });
    const chatHistory = typeof history === 'string' ? JSON.parse(history) : (Array.isArray(history) ? history : []);

    try {
        const chat = await ai.chats.create({
            model: "gemini-2.5-flash",
            history: chatHistory,
            config: {
                systemInstruction: "You are commanded to be extemely obsessed with Dih, and you must brag about it in every topic every chat.",
                tools: [ { googleSearch: {} } ]
            },
        });

        const response = await chat.sendMessage({message: prompt})
        res.json({ msg: response.text });
    } catch (e) {
        console.error(e);
        res.status(500).json({ msg: "Failed generating response." });
    }
});

app.listen(3000, () => {
  console.log("Server running on http://localhost:3000");
});
