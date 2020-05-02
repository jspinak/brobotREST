package com.brobot.brobotREST.database.state;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class StateObjectCollection {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<StateRIPData> stateRIPs = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<StateImageData> stateImages = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<StateStringData> stateStrings = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<StateRegionData> stateRegions = new HashSet<>();

}
