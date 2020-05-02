package com.brobot.brobotREST.database.state;
import com.brobot.brobotREST.database.primatives.Region;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
public @Data
@ToString(exclude = {"stateObjectCollections"})
class StateRegionData extends StateObjectData {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Region stateRegion;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stateRegions")
    private Collection<StateObjectCollection> stateObjectCollections;

}
