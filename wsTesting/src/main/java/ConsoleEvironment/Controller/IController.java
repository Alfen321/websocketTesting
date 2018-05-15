package ConsoleEvironment.Controller;

import ConsoleEvironment.Console.IOutput;

public interface IController extends IOutput {
    
    public void runCommand(String command);
}
