package ConsoleEvironment.CommandHandler;

public class CommandHandlerRaw implements ICommandHandler {

    @Override
    public boolean setup() {
        return true;
    }

    @Override
    public String command(String command) {
        return command;
    }

    @Override
    public void terminate() {
    }

}
