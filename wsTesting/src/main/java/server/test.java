package server;

import Implements.LinuxSanitizingImplementation;
import Implements.WindowsSanitizingImplementation;
import Interfaces.SanitizingInputInterface;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.util.concurrent.Executors;
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
    SanitizingInputInterface sanitizing = null;

    @OnOpen
    public void handleOpen(Session userSession) {
        System.out.println("client connected...");
        this.userSession = userSession;
        sendMessage("Welcome");
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            sendMessage("I am a Windows based server");
        } else {
            sendMessage("I am a Unknown based server");
        }
    }

    @OnClose
    public void handleClose() {
        System.out.println("client disconnected...");
    }

    @OnMessage
    public void onMessage(String message) throws InterruptedException {
        String resultMessage = "";
        Process p = null;
        if (message != null) {
            System.out.println(message);
            try {
                if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                    sanitizing = new WindowsSanitizingImplementation();
                    String saniText = sanitizing.sanitize(message);
                    if(!saniText.equals("Illegal input!")){
                        p = Runtime.getRuntime().exec("cmd /c " + saniText);                    
                    }else{
                        sendMessage(saniText );
                    }
                } else {
                    sanitizing = new LinuxSanitizingImplementation();
                    p = Runtime.getRuntime().exec("sh -c " + sanitizing.sanitize(message));
                }
                InputStream in = p.getInputStream();
                InputStream inf = p.getErrorStream();
//                
//                if(ins.available() != 0){
//                    in = ins;
//                }
                sendMessage("StartOfReturnBlock");
                int c;
                while ((c = in.read()) != -1) {
                    resultMessage += (char) c;
                    if (c == 10) {
                        sendMessage(resultMessage);
                        resultMessage = "";
                    }
                }
                sendMessage("EndOfReturnBlock");
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
