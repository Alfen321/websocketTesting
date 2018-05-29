package ConsoleEvironment.Console;

import java.util.Observable;


public class ConsoleObservable extends Observable {
    
    public void sendOutput(String output) {
        this.setChanged();
        this.notifyObservers(output);
    }
}
