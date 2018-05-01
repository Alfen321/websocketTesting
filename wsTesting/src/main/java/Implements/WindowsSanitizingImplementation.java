package Implements;

import java.util.ArrayList;
import java.util.List;
import Interfaces.SanitizingInputInterface;

public class WindowsSanitizingImplementation implements SanitizingInputInterface{

    List<String> illegalInputs = new ArrayList<String>();

    public WindowsSanitizingImplementation() {
        illegalInputs.add("del");
    }
    
    @Override
    public String sanitize(String message) {
    
        for (String illegalInput : illegalInputs) {
            if(message.toLowerCase().contains(message)){
                return "Illegal input!";
            }
        }
        
        return message;
    }
    
}
