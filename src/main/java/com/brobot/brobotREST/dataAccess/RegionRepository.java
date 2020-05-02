package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.database.state.CoexistingState;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, Long> {
}
