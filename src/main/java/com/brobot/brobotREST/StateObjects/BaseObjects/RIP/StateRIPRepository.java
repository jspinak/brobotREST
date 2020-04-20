package com.brobot.brobotREST.StateObjects.BaseObjects.RIP;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public @Data
class StateRIPRepository {

    // you could check to see if the images exist in the filepath
    List<StateRIP> stateRIPs = new ArrayList<>();

}
