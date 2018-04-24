package server;

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
    public void onMessage(String message) {
        if(message != null){
            System.out.println(message);
            sendMessage(message);
        }
    }
    
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }
    
}
