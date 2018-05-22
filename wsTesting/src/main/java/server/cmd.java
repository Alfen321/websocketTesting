package server;

import ConsoleEvironment.CommandHandler.CommandHandlerRaw;
import ConsoleEvironment.Console.Console;
import ConsoleEvironment.Console.IOutput;
import ConsoleEvironment.Controller.WebsocketController;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/cmd")
public class cmd implements IOutput {

    Session userSession = null;
    WebsocketController wsController = null;

    @OnOpen
    public void handleOpen(Session userSession) {
        this.userSession = userSession;
        System.out.println("client connected...");
        wsController = new WebsocketController(this, new Console(), new CommandHandlerRaw());
    }

    @OnClose
    public void handleClose() {
        System.out.println("client disconnected...");
        wsController.terminate();
    }

    @OnMessage
    public void onMessage(String message) throws InterruptedException {
        wsController.runCommand(message);
    }

    public void sendMessage(String message) {
        try {
            this.userSession.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void send(String message) {
        sendMessage(message);
    }
}
