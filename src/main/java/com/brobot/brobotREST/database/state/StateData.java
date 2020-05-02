package com.brobot.brobotREST.database.state;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StateData {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> stateText = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private StateObjectCollection stateObjects = new StateObjectCollection();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private StateObjectCollection activatingObjects = new StateObjectCollection();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "stateData", orphanRemoval = true)
    private Set<CoexistingState> coexistingStates = new HashSet<>();
    private boolean onTopOfActivatingState = false;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate lastAccessed;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime lastTimeAccessed;

}
