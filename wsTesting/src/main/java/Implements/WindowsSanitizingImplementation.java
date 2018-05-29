package Implements;

import Interfaces.DataConnectorInterface;
import java.util.ArrayList;
import java.util.List;
import Interfaces.SanitizingInputInterface;

public class WindowsSanitizingImplementation implements SanitizingInputInterface{

    DataConnectorInterface DataConn = null;
    List<String> illegalInputs = null;

    public WindowsSanitizingImplementation(boolean _whitelist) {
        if(_whitelist){
            DataConn = new FileImplementation("Windows", true);
        }else{
            DataConn = new FileImplementation("Windows", false);
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
