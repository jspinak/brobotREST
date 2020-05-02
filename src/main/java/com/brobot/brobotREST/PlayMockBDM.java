package com.brobot.brobotREST;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.brobots.BDM.Tests.MockBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateRIPData;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class PlayMockBDM {

    public static void main(String[] args) {
        // This is how normal Spring Boot app would be launched
        // SpringApplication.run(SpringBootExampleApplication.class, args);

        // JavaFxApplication doesn't exist yet,
        // we'll create it in the next step
        //Application.launch(JavaFxApplication.class, args);

        SpringApplicationBuilder builder = new SpringApplicationBuilder(PlayMockBDM.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        MockBDM blackDesert = context.getBean(MockBDM.class);

        List<StateData> allStates = blackDesert.getAllStates();
        List<StateRIPData> allRIPs = blackDesert.getAllRIPs();
        blackDesert.printState(StateEnumsBDM.CAMP);
        blackDesert.printState(StateEnumsBDM.GARTEN);
        blackDesert.printRatios();
        blackDesert.printTree(StateEnumsBDM.CAMP);
        blackDesert.testFinder(StateEnumsBDM.CAMP_SIDEBAR, StateEnumsBDM.POSTEN);
        blackDesert.testFinder(StateEnumsBDM.CAMP_SIDEBAR, StateEnumsBDM.ERGEBNIS_DES_POSTEN_HANDELS);
        blackDesert.testFinder(StateEnumsBDM.POSTEN, StateEnumsBDM.KOMMANDOPOSTEN);
        blackDesert.gotoState(StateEnumsBDM.KOMMANDOPOSTEN);
    }
}
