package com.brobot.brobotREST.mock;

import com.brobot.brobotREST.actions.ActionOptions;
import com.brobot.brobotREST.database.state.stateObject.StateObject;
import org.sikuli.script.Match;

import java.util.HashMap;
import java.util.Map;

public class Report {

    public enum OutputLevel {
        NONE, LOW, HIGH
    }

    public static Map<OutputLevel, Integer> outputLevels = new HashMap<>();
    static {
        outputLevels.put(OutputLevel.NONE, 0);
        outputLevels.put(OutputLevel.LOW, 1);
        outputLevels.put(OutputLevel.HIGH, 2);
    }

    public static OutputLevel outputLevel = OutputLevel.HIGH;
    public static int MaxMockMatchesFindAll = 10;

    public static boolean minReportingLevel(OutputLevel level) {
        return outputLevels.get(level) <= outputLevels.get(outputLevel);
    }

    public static boolean print(Match match, StateObject stateObject, ActionOptions actionOptions) {
        if (minReportingLevel(OutputLevel.LOW))
            System.out.format("%s: %s| ", actionOptions.getAction(), stateObject.getName());
        if (minReportingLevel(OutputLevel.HIGH))
            System.out.format("%s: %s, match=%s| ", actionOptions.getAction(), stateObject.getName(), match.toString());
        return true;
    }
}
