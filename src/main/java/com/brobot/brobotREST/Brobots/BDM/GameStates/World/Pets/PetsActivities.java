package com.brobot.brobotREST.Brobots.BDM.GameStates.World.Pets;

import com.brobot.brobotREST.StateObjects.ObjectMethods.AllGameObjectActions;
import org.springframework.stereotype.Component;

@Component
public class PetsActivities {

    private final Pets pets;
    private final AllGameObjectActions we;

    public PetsActivities(Pets pets, AllGameObjectActions we) {
        this.pets = pets;
        this.we = we;
    }

    public boolean train() {
        System.out.print(" train pet | ");
        if (we.click().RIP(pets.getTrainingAbgeschlossen()) || we.click().RIP(pets.getFertig())) {
            System.out.print(" training abgeschlossen | ");
            we.pause(1.0);
        }
        if (!we.click().RIP(pets.getTrainingAbgeschlossen())) return false;
        we.waitForRIP().wait(2.0, pets.getPetTalentTraining());
        we.click().RIP(pets.getPetTalentTraining());
        we.pause(1.0);
        we.click().RIP(pets.getFertig()); //bestatigen
        we.pause(1.0);
        return true;
    }
/*
    public boolean alleFuttern() {
        boolean alleGefuttert = true;
        bdoGameRegion.waitImage(petFoodIcon, 2.0);
        List<DefineRegion> petMatches = bdoGameRegion.findAllDefineRegions(petFoodIcon);
        for (DefineRegion petMatch : petMatches) {
            if (futterEinPet(petMatch)) alleGefuttert = false;
        }
        return alleGefuttert;
    }

    private boolean futterEinPet(DefineRegion pet) {
        if (pet.clickSicher()) {
            bdoGameRegion.wait(1.5);
            if (trainingAbgeschlossen.clickSicher()) bdoGameRegion.wait(.5);
            return futtern.clickAndDefineRegion();
        }
        return false;
    }

    public boolean trainAndFeed() {
        //if (!open()) return false;
        boolean gefuttert = alleFuttern();
        boolean trained = train();
        //close();
        return trained || gefuttert;
    }

    public boolean futterOnly() {
        //if (!open()) return false;
        boolean gefuttert = alleFuttern();
        //close();
        return gefuttert;
    }

 */

}