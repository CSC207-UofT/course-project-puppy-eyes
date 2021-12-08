package server.use_cases.pet_use_cases.pet_interactor.interactions;

import server.use_cases.ResponseModel;

/**
 * A context class responsible for executing a given strategy of PetInteraction.
 */
public class PetInteractionContext {

    private PetInteraction interaction;

    public void setInteraction(PetInteraction interaction) {
        this.interaction = interaction;
    }

    public ResponseModel interact(int pet1Id, int pet2Id) {
        return interaction.interact(pet1Id, pet2Id);
    }

}
