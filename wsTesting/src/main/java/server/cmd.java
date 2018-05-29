package server;

import ConsoleEvironment.CommandHandler.CommandHandlerBlacklist;
import ConsoleEvironment.CommandHandler.CommandHandlerRaw;
import ConsoleEvironment.Console.Console;
import ConsoleEvironment.Console.ConsoleObservable;
import ConsoleEvironment.Controller.WebsocketController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/cmd")
public class cmd implements Observer {

    Session userSession = null;
    WebsocketController wsController = null;

    @OnOpen
    public void handleOpen(Session userSession) {
        this.userSession = userSession;
        System.out.println("client connected...");
        ConsoleObservable consoleObs = new ConsoleObservable();
        consoleObs.addObserver(this);
        wsController = new WebsocketController(new Console(consoleObs), new CommandHandlerRaw());
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
    public void update(Observable o, Object arg) {
        String consoleOutput = (String) arg;
        sendMessage(consoleOutput);
    }
}
