package ConsoleEvironment.Console;

import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;

public class ConsoleQueue extends Observable {
    ArrayBlockingQueue<String> outputQueue = null;

    public ConsoleQueue() {
        this.outputQueue = new ArrayBlockingQueue(50);
    }
    
    public boolean addToQueue(String output) {
        boolean didPlace = outputQueue.offer(output);
        this.notifyObservers();
        return didPlace;
    }
    
    public String getNextOutput() {
        return outputQueue.poll();
    }
    
    public boolean queueHasNext() {
        return !outputQueue.isEmpty();
    }
}
