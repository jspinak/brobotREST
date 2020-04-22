package com.brobot.brobotREST;

import com.brobot.brobotREST.Brobots.BDM.GameStates.GameStateEnumsBDM;
import com.brobot.brobotREST.Brobots.BDM.Tests.MockBDM;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

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

        blackDesert.printGameState(GameStateEnumsBDM.CAMP);
        blackDesert.printGameState(GameStateEnumsBDM.GARTEN);
        blackDesert.printRatios();
        blackDesert.printTree(GameStateEnumsBDM.CAMP);
        blackDesert.testFinder(GameStateEnumsBDM.CAMP_SIDEBAR, GameStateEnumsBDM.POSTEN);
        blackDesert.testFinder(GameStateEnumsBDM.CAMP_SIDEBAR, GameStateEnumsBDM.ERGEBNIS_DES_POSTEN_HANDELS);
        blackDesert.testFinder(GameStateEnumsBDM.POSTEN, GameStateEnumsBDM.KOMMANDOPOSTEN);
        blackDesert.gotoState(GameStateEnumsBDM.KOMMANDOPOSTEN);
    }
}
