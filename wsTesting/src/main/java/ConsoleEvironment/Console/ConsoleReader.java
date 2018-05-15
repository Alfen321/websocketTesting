package ConsoleEvironment.Console;


import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private Scanner in = null;
    private volatile boolean running = true;
    private IOutput output = null;

    public ConsoleReader(InputStream stream, IOutput output) {
        this.in = new Scanner(stream);
        this.output = output;
    }

    @Override
    public void run() {
        while (running) {
            if (in.hasNextLine()) {
                output.send(in.nextLine());
            }
        }
    }

    public void terminate() {
        running = false;
    }

}
