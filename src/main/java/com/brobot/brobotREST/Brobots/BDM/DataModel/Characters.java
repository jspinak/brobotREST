package com.brobot.brobotREST.Brobots.BDM.DataModel;

import com.brobot.brobotREST.Primatives.Image;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Characters {
    private Image magierinIcon = new Image("magierinCharakter","magierinCharakterGewahlt");

    public enum Klasse {
        MAGIERIN, WALDLAUFERIN, WALKURE, KRIEGER, RIESE
    }

    public enum Name {
        SENILIS, PLANA, MMMEATLOAF
    }

    private static Map<Name, Character> characters = new HashMap<>();
    {
        addCharacterToMap(Name.SENILIS, Klasse.MAGIERIN);
        addCharacterToMap(Name.PLANA, Klasse.WALDLAUFERIN);
        addCharacterToMap(Name.MMMEATLOAF, Klasse.WALDLAUFERIN);
    }

    private static Map<Klasse, Image> klassen = new HashMap<>();
    {
        klassen.put(Klasse.MAGIERIN, magierinIcon);
    }

    private void addCharacterToMap(Name name, Klasse klasse) {
        Character newCharacter = new Character(name, klasse);
        characters.put(name, newCharacter);
    }

    public Character getCharacter(Name name) {
        return characters.get(name);
    }

    public Image getKlasseImage(Name characterName) {
        Character character = getCharacter(characterName);
        Klasse klasse = character.getKlasse();
        return klassen.get(klasse);
    }
}
