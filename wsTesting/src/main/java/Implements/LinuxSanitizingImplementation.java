package Implements;

import Interfaces.DataConnectorInterface;
import Interfaces.InputSanitizeInterface;

import java.util.List;

public class LinuxSanitizingImplementation implements InputSanitizeInterface {

    DataConnectorInterface DataConn = null;
    List<String> illegalInputs = null;

    public LinuxSanitizingImplementation(boolean _whitelist) {
        if (_whitelist) {
            DataConn = new FileImplementation("Linux", "whitelist");
        } else {
            DataConn = new FileImplementation("Linux", "blacklist");
        }

        illegalInputs = DataConn.retrieveIllegalInputs();
    }

    @Override
    public String sanitize(String message) {

        for (String illegalInput : illegalInputs) {
            if (message.toLowerCase().contains(illegalInput)) {
                return "Illegal input!";
            }
        }

        return message;
    }

}
