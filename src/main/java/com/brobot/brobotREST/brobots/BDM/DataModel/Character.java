package com.brobot.brobotREST.brobots.BDM.DataModel;

public class Character {

    private Characters.Name name;
    private Characters.Klasse klasse;

    public Character(Characters.Name name, Characters.Klasse klasse) {
        this.name = name;
        this.klasse = klasse;
    }

    public Characters.Klasse getKlasse() {
        return klasse;
    }
}
