package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.state.StateRIPData;
import com.brobot.brobotREST.database.state.StateRegionData;
import org.springframework.data.repository.CrudRepository;

public interface StateRegionRepository extends CrudRepository<StateRegionData, Long> {
}
