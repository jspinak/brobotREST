package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.state.StateRIPData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StateRIPRepository extends CrudRepository<StateRIPData, Long> {
    List<StateRIPData> findAll();
}
