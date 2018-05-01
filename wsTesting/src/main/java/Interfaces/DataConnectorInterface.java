package Interfaces;

import java.util.List;

public interface DataConnectorInterface {
    
    List retrieveIllegalInputs();
    boolean saveIllegalInput(String input);
}
