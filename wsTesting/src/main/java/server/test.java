package server;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/test")
public class test {
    
    @OnOpen
    public void handleOpen() {
        System.out.println("client connected...");
    }
    
    @OnClose
    public void handleClose() {
        System.out.println("client disconnected...");
    }
    
    @OnMessage
    public String onMessage(String message) {
        System.out.println(message);
        return message;
    }
    
}
