package com.brobot.brobotREST.database.state;

import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.database.primatives.RegionImagePairs;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"stateObjectCollections"})
public class StateRIPData extends StateObjectData {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Region searchRegion;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RegionImagePairs regionImagePairs = new RegionImagePairs();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stateRIPs")
    private Collection<StateObjectCollection> stateObjectCollections;

}
