package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.database.state.StateRIPData;
import org.springframework.data.repository.CrudRepository;

public interface StateImageRepository extends CrudRepository<StateImageData, Long> {
}
