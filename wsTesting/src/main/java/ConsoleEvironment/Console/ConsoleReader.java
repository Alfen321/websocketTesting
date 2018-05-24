package ConsoleEvironment.Console;


import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private Scanner in = null;
    private volatile boolean running = true;
    private ConsoleQueue cq = null;

    public ConsoleReader(InputStream stream, ConsoleQueue cq) {
        this.in = new Scanner(stream);
        this.cq = cq;
    }

    @Override
    public void run() {
        while (running) {
            if (in.hasNextLine()) {
                cq.addToQueue(in.nextLine());
            }
        }
    }

    public void terminate() {
        running = false;
    }

}
