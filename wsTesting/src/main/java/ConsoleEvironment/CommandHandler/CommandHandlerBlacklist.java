package ConsoleEvironment.CommandHandler;

import Implements.LinuxSanitizingImplementation;
import Implements.WindowsSanitizingImplementation;
import Interfaces.SanitizingInputInterface;

public class CommandHandlerBlacklist implements ICommandHandler {

    SanitizingInputInterface sanitizing = null;

    @Override
    public boolean setup() {
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            sanitizing = new WindowsSanitizingImplementation(false);
        } else {
            sanitizing = new LinuxSanitizingImplementation(false);
        }
        return true;
    }

    @Override
    public String command(String command) {
        String saniText = sanitizing.sanitize(command);
        if (!saniText.equals("Illegal input!")) {
            return saniText;
        }else{
            return saniText;
        }
    }

    @Override
    public void terminate() {
    }

}
