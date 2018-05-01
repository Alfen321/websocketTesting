package Implements;

import Interfaces.SanitizingInputInterface;
import java.util.ArrayList;
import java.util.List;

public class LinuxSanitizingImplementation implements SanitizingInputInterface{

    List<String> illegalInputs = new ArrayList<String>();

    public LinuxSanitizingImplementation() {
        illegalInputs.add("rm");
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
