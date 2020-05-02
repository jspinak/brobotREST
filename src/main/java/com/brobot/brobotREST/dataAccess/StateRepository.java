package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.brobots.BDM.GameStates.StateEnumsBDM;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRepository extends CrudRepository<StateData, Long> {
    List<StateData> findAll();
    StateData findByName(String stateEnumAsString);
}
