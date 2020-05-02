package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.primatives.RegionImagePair;
import com.brobot.brobotREST.database.state.CoexistingState;
import org.springframework.data.repository.CrudRepository;

public interface RegionImagePairRepository extends CrudRepository<RegionImagePair, Long> {
}
