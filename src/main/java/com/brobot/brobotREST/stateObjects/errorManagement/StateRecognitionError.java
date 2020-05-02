package com.brobot.brobotREST.stateObjects.errorManagement;

public interface StateRecognitionError {
    boolean fehlerBeheben();
    boolean makeOpenStatePossible();
    boolean quitGameAndRerun();
}
