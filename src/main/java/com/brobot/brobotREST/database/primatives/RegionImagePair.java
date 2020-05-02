package com.brobot.brobotREST.database.primatives;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class RegionImagePair {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Region region = new Region();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image image;


}
