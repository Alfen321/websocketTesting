package Implements;

import Interfaces.DataConnectorInterface;
import Interfaces.SanitizingInputInterface;
import java.util.ArrayList;
import java.util.List;

public class LinuxSanitizingImplementation implements SanitizingInputInterface{
    
    DataConnectorInterface DataConn = null;
    List<String> illegalInputs = DataConn.retrieveIllegalInputs();

    @Override
    public String sanitize(String message) {
    
        for (String illegalInput : illegalInputs) {
            if(message.toLowerCase().contains(illegalInput)){
                return "Illegal input!";
            }
        }
        
        return message;
    }
    
}
