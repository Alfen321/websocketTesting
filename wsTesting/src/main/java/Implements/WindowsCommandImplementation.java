package Implements;

import Interfaces.DataConnectorInterface;
import Interfaces.InputCommandInterface;

import java.util.List;

public class WindowsCommandImplementation implements InputCommandInterface {

    DataConnectorInterface DataConn = null;
    List<String> illegalInputs = null;

    public WindowsCommandImplementation() {
        DataConn = new FileImplementation("Windows", "command");

        illegalInputs = DataConn.retrieveIllegalInputs();
    }

    public String command(String message){
        for (String illegalInput : illegalInputs) {
            System.out.println(illegalInput);
            if(illegalInput.toLowerCase().contains(message.toLowerCase())){
                return illegalInput.split("=.=")[1];
            }
        }
        return "No command like this!";
    }
}
