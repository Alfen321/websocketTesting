package ConsoleEvironment.Controller;

import ConsoleEvironment.CommandHandler.ICommandHandler;
import ConsoleEvironment.Console.IConsole;
import ConsoleEvironment.Console.IOutput;

public class WebsocketController implements IController {

    private IOutput output = null;
    private IConsole console = null;
    private ICommandHandler commandHandler = null;

    public WebsocketController(IOutput output, IConsole console, ICommandHandler commandHandler) {
        this.output = output;
        this.console = console;
        this.commandHandler = commandHandler;

        this.console.setup(this);
        this.commandHandler.setup();
    }

    @Override
    public void send(String message) {
        output.send(message);
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
