package ConsoleEvironment.CommandHandler;

import ConsoleEvironment.Console.IOutput;

public interface ICommandHandler {

    public boolean setup();

    public String command(String command);

    public void terminate();
}
