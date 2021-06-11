package com.brobot.brobotREST.actions;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.stereotype.Component;

@Component
public class NullState {

    // convert simple objects to state objects

    public enum Enum implements StateEnum {
        NULL
    }

}
