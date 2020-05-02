package com.brobot.brobotREST.dataAccess;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.state.CoexistingState;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
