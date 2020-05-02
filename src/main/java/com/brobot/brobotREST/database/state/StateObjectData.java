package com.brobot.brobotREST.database.state;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@MappedSuperclass
public abstract class StateObjectData {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private int probabilityExists = 100;
    private boolean staysVisibleAfterClicked = false;
    private String ownerStateName;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> statesActivatedOnClick = new HashSet<>();
    private double timeToWaitAfterAction;

}
