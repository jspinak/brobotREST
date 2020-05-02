package com.brobot.brobotREST.database.state;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
public @Data
@ToString(exclude = {"stateObjectCollections"})
class StateStringData extends StateObjectData {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String string;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stateStrings")
    private Collection<StateObjectCollection> stateObjectCollections;

}
