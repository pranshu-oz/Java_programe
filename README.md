# 💬 Java ChatBox Client-Server Application

## 📝 Overview

This project is a simple Java-based chat application with a graphical user interface (GUI) using Swing. It allows multiple clients to connect to a server, send messages in real-time, and view the list of active users.

## ✨ Features

- 👥 Multi-user chat with real-time message broadcasting
- 🔑 User login panel (username and server IP input)
- 📋 User list display and updates
- 🗂️ Message history saved on the server
- 🖼️ GUI splash screen and chat interface

## 📁 File Structure

- `MyServer.java`: Server implementation, manages clients and message broadcasting.
- `MyClient.java`: Client-side message handling and UI updates.
- `UIdesign.java`: Main chat UI and network connection logic.
- `Login.java`: Login panel for user credentials.
- `manifest.txt`: JAR manifest specifying the main class.
- `talk.jpg`: Image used in the splash screen.
- Compiled `.class` files and `message.jar`: Build artifacts.

## 🚀 How to Run

1. **Compile the source files:**
   ```sh
   javac *.java
   ```

2. **Start the server:**
   ```sh
   java MyServer
   ```

3. **Start the client(s):**
   ```sh
   java UIdesign
   ```
   - Enter your username and the server IP address in the login panel.

4. **Build JAR (optional):**
   ```sh
   jar cfm message.jar manifest.txt *.class talk.jpg
   ```

## 🖥️ Requirements

- Java SE 8 or higher
- Swing (included in standard Java)
- All files should be in the same directory

## ⚠️ Notes

- The server saves chat history to `E:\fileDataSave.txt`. Ensure this file and path exist or update the code for your environment.
- The application uses basic networking and is intended for LAN/local use.

## 📜 License

This project is for educational purposes.
