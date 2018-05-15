package ConsoleEvironment.CommandHandler;

import ConsoleEvironment.Console.IOutput;

public interface ICommandHandler {

    public boolean setup();

    public String command(String commmand);

    public void terminate();
}
