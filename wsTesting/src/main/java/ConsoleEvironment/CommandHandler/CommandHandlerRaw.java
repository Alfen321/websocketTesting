package ConsoleEvironment.CommandHandler;

import ConsoleEvironment.Console.IOutput;

public class CommandHandlerRaw implements ICommandHandler{
    
    @Override
    public boolean setup() {
        return true;
    }

    @Override
    public String command(String commmand) {
        return commmand;
    }

    @Override
    public void terminate() {
    }

}
