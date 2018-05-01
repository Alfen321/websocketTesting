package Implements;

import Interfaces.DataConnectorInterface;
import java.util.ArrayList;
import java.util.List;
import Interfaces.SanitizingInputInterface;

public class WindowsSanitizingImplementation implements SanitizingInputInterface{

    /*List<String> illegalInputs = new ArrayList<String>();

    public WindowsSanitizingImplementation() {
        illegalInputs.add("del");
    }*/
    
    DataConnectorInterface DataConn = new FileImplementation();
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
