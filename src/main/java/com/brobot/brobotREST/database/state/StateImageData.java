package com.brobot.brobotREST.database.state;

import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@ToString(exclude = {"stateObjectCollections"})
public class StateImageData extends StateObjectData {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Region searchRegion;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image image;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stateImages")
    private Collection<StateObjectCollection> stateObjectCollections;

}
