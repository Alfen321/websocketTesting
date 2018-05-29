package Implements;

import Interfaces.DataConnectorInterface;

import java.util.List;
import Interfaces.InputSanitizeInterface;

public class WindowsSanitizingImplementation implements InputSanitizeInterface {

    DataConnectorInterface DataConn = null;
    List<String> illegalInputs = null;

    public WindowsSanitizingImplementation(boolean _whitelist) {
        if(_whitelist){
            DataConn = new FileImplementation("Windows", "whitelist");
        }else{
            DataConn = new FileImplementation("Windows", "blacklist");
        }
        
        illegalInputs = DataConn.retrieveIllegalInputs();
    }
    
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
