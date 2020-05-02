package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.state.StateObjectCollection;
import com.brobot.brobotREST.database.state.StateRIPData;
import org.springframework.data.repository.CrudRepository;

public interface StateObjectCollectionRepository extends CrudRepository<StateObjectCollection, Long> {
}
