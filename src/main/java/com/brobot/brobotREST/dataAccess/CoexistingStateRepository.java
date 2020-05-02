package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.state.CoexistingState;
import com.brobot.brobotREST.database.state.StateRIPData;
import org.springframework.data.repository.CrudRepository;

public interface CoexistingStateRepository extends CrudRepository<CoexistingState, Long> {
}
