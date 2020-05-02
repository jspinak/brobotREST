package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.primatives.RegionImagePairs;
import com.brobot.brobotREST.database.state.CoexistingState;
import org.springframework.data.repository.CrudRepository;

public interface RegionImagePairsRepository extends CrudRepository<RegionImagePairs, Long> {
}
