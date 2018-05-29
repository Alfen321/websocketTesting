package ConsoleEvironment.Console;


public interface IConsole {
    
    public boolean setup();

    public boolean isAlive();

    public void runCommand(String command);

    public void terminate();
}
