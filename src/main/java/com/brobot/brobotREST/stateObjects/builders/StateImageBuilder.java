package com.brobot.brobotREST.stateObjects.builders;

import com.brobot.brobotREST.dataAccess.StateImageRepository;
import com.brobot.brobotREST.database.state.StateData;
import com.brobot.brobotREST.database.state.StateImageData;
import com.brobot.brobotREST.stateData.methods.StateDataMethods;
import com.brobot.brobotREST.stateData.methods.StateImageMethods;
import com.brobot.brobotREST.primatives.enums.StateEnum;
import com.brobot.brobotREST.database.primatives.Image;
import com.brobot.brobotREST.database.primatives.Region;
import com.brobot.brobotREST.web.services.StateService;
import org.springframework.stereotype.Component;

@Component
public class StateImageBuilder implements StateObjectBuilder {

    private StateBuilderGeneric stateBuilderGeneric;
    private StateDataMethods stateDataMethods;
    private StateImageMethods stateImageMethods;
    private StateService stateService;
    private StateImageRepository stateImageRepository;
    private StateImageData stateImage;

    public StateImageBuilder(StateBuilderGeneric stateBuilderGeneric,
                             StateDataMethods stateDataMethods,
                             StateImageMethods stateImageMethods,
                             StateService stateService,
                             StateImageRepository stateImageRepository) {
        this.stateBuilderGeneric = stateBuilderGeneric;
        this.stateDataMethods = stateDataMethods;
        this.stateImageMethods = stateImageMethods;
        this.stateService = stateService;
        this.stateImageRepository = stateImageRepository;
    }

    public StateImageBuilder init(String name) {
        stateImage = new StateImageData();
        stateImageMethods.setOwnerName(stateImage, name);
        stateImageRepository.save(stateImage);
        StateData newState = stateBuilderGeneric.getNewState();
        stateDataMethods.addStateImages(newState, stateImage);
        return this;
    }

    public StateImageBuilder setName(String string) {
        stateImageMethods.setName(stateImage, string);
        return this;
    }

    public StateImageBuilder setSearchRegion(Region defineRegion) {
        stateImage.setSearchRegion(defineRegion);
        return this;
    }

    public StateImageBuilder setImage(String... imageNames) {
        stateImage.setImage(new Image(imageNames));
        if (stateImageMethods.getName(stateImage) == null) {
            stateImageMethods.setName(stateImage, imageNames[0]);
        }
        return this;
    }

    public StateImageBuilder setStaysVisibleAfterClicked() {
        stateImageMethods.setStaysVisibleAfterClicked(stateImage,true);
        return this;
    }

    public StateImageBuilder addStatesToActivate(StateEnum... statesToActivate) {
        for (StateEnum stateEnum : statesToActivate) {
            stateImageMethods.addStateToActivate(stateImage, stateEnum.toString());
            StateData stateData = stateService.initAndSaveIfDoesntExist(stateEnum);
            stateDataMethods.addActivatingImage(stateData, stateImage);
            stateService.save(stateData); //not the same state as in stateBuilder
        }
        return this;
    }

    public StateImageBuilder setTimeToWaitAfterAction(double secondsToWaitAfterClick) {
        stateImageMethods.setTimeToWaitAfterAction(stateImage, secondsToWaitAfterClick);
        return this;
    }

    public StateImageData build() {
        stateImageRepository.save(stateImage);
        //stateBuilderGeneric.save();
        return stateImage;
    }

}
