package com.brobot.brobotREST.database.state;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class CoexistingState {

    @Id
    @GeneratedValue
    private Long id;
    private String coexistingState;
    private boolean alwaysPresent = true;

    @ManyToOne
    private StateData stateData;

    public CoexistingState() {}

    public CoexistingState(String coexistingState, boolean alwaysPresent) {
        this.coexistingState = coexistingState;
        this.alwaysPresent = alwaysPresent;
    }

}
