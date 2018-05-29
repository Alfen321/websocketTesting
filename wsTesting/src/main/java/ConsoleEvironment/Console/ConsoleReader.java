package ConsoleEvironment.Console;


import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private Scanner in = null;
    private volatile boolean running = true;
    private ConsoleObservable consoleObs = null;

    public ConsoleReader(InputStream stream, ConsoleObservable consoleObs) {
        this.in = new Scanner(stream);
        this.consoleObs = consoleObs;
    }

    @Override
    public void run() {
        while (running) {
            if (in.hasNextLine()) {
                consoleObs.sendOutput(in.nextLine());
            }
        }
    }

    public void terminate() {
        running = false;
    }

}
