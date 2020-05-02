package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateStringData;
import org.springframework.data.repository.CrudRepository;

public interface StateStringRepository extends CrudRepository<StateStringData, Long> {
}
