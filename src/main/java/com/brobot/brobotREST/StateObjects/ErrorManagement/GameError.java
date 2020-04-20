package com.brobot.brobotREST.StateObjects.ErrorManagement;

public interface GameError {
    boolean fehlerBeheben();
    boolean makeOpenStatePossible();
    boolean quitGameAndRerun();
}
