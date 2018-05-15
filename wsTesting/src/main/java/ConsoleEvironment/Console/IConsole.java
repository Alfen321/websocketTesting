package ConsoleEvironment.Console;


public interface IConsole {
    
    public boolean setup(IOutput output);

    public boolean isAlive();

    public void runCommand(String command);

    public void terminate();
}
