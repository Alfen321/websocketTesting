package ConsoleEvironment.CommandHandler;

import Implements.WindowsCommandImplementation;
import Interfaces.InputCommandInterface;

public class CommandHandlerCommand implements ICommandHandler {

    InputCommandInterface command = null;

    @Override
    public boolean setup() {
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            command = new WindowsCommandImplementation();
        }
        return true;
    }

    @Override
    public String command(String message) {
        return command.command(message);
    }

    @Override
    public void terminate() {

    }
}
