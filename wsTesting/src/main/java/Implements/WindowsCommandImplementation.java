package Implements;

import Interfaces.DataConnectorInterface;
import Interfaces.InputCommandInterface;
import java.util.List;

public class WindowsCommandImplementation implements InputCommandInterface {

    DataConnectorInterface DataConn = null;
    List<String> commandList = null;

    public WindowsCommandImplementation() {
        DataConn = new FileImplementation("Windows", "command");

        commandList = DataConn.retrieveIllegalInputs();
    }

    public String command(String message){
        for (String command : commandList) {
            System.out.println(command);
            if(command.toLowerCase().contains(message.toLowerCase())){
                return command.split("=.=")[1];
            }
        }
        return "No command like this!";
    }
}
