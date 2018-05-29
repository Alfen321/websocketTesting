package ConsoleEvironment.Controller;

import ConsoleEvironment.CommandHandler.ICommandHandler;
import ConsoleEvironment.Console.IConsole;

public class WebsocketController implements IController {

    private IConsole console = null;
    private ICommandHandler commandHandler = null;

    public WebsocketController(IConsole console, ICommandHandler commandHandler) {
        this.console = console;
        this.commandHandler = commandHandler;

        this.console.setup();
        this.commandHandler.setup();
    }

    @Override
    public void runCommand(String command) {
        console.runCommand(commandHandler.command(command));
    }
    
    public void terminate(){
        console.terminate();
        commandHandler.terminate();
    }
}
