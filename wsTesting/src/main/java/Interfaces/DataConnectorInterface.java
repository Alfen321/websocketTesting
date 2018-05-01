package Interfaces;

import java.util.ArrayList;

public interface DataConnectorInterface {
    
    ArrayList retrieveillegalInputs();
    Boolean saveIllegalInput(String input);
}
