package ConsoleEvironment.CommandHandler;


public interface ICommandHandler {

    public boolean setup();

    public String command(String command);

    public void terminate();
}
