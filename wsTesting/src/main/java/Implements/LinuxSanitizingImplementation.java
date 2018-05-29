package Implements;

import Interfaces.DataConnectorInterface;
import Interfaces.SanitizingInputInterface;
import java.util.ArrayList;
import java.util.List;

public class LinuxSanitizingImplementation implements SanitizingInputInterface {

    DataConnectorInterface DataConn = null;
    List<String> illegalInputs = null;

    public LinuxSanitizingImplementation(boolean _whitelist) {
        if (_whitelist) {
            DataConn = new FileImplementation("Linux", true);
        } else {
            DataConn = new FileImplementation("Linux", false);
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
