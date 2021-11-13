package server.drivers.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import server.ServerApplication;
import server.drivers.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a tuple, storing an input's prompt
 * and the name of the input.
 */
class PromptAndInputNameTuple {
    private final String prompt;
    private final String inputName;

    public PromptAndInputNameTuple(String prompt, String inputName) {
        this.prompt = prompt;
        this.inputName = inputName;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getInputName() {
        return inputName;
    }
}

/**
 * A Spring runner which is responsible for running, processing IO,
 * and handling the command line interface to the back-end.
 */
@Component
public class CmdLineRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    private final CmdLineGateway gateway;
    private final IOSystem ioSystem;

    public CmdLineRunner(CmdLineGateway gateway, IOSystem ioSystem) {
        this.gateway = gateway;
        this.ioSystem = ioSystem;
    }

    /**
     * For each PromptAndInputName in tuples, prompt the user and retrieve
     * the user's input for the corresponding input name.
     *
     * @return A mapping from input names to the input entered
     * by a user.
     */
    private Map<String, String> getCommandInputs(PromptAndInputNameTuple[] tuples){
        Map<String, String> inputs = new HashMap<>();

        for (PromptAndInputNameTuple tuple : tuples) {
            inputs.put(tuple.getInputName(), ioSystem.getInput(tuple.getPrompt()));
        }

        return inputs;
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * createUser command. The mapping is of the form:
     *
     *  firstName -> user's entered first name
     *  lastName -> user's entered last name
     *  current address -> user's entered current address
     *  current city -> user's entered current city
     *  password -> users' entered password.
     *  email -> user's entered email
     */
    public Map<String, String> getCreateUserAccountInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter your first name: ", "firstName"),
                new PromptAndInputNameTuple("Enter your last name: ", "lastName"),
                new PromptAndInputNameTuple("Enter your current address: ", "currentAddress"),
                new PromptAndInputNameTuple("Enter your current city: ", "currentCity"),
                new PromptAndInputNameTuple("Enter your password: ", "password"),
                new PromptAndInputNameTuple("Enter your email: ", "email")
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * fetchUserAccount command. The mapping is of the form:
     *
     *  userId -> user's entered user id
     */
    public Map<String, String> getFetchUserAccountInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter a user id: ", "userId")
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * editUserAccount command. The mapping is of the form:
     *
     *  userId -> user's entered user id
     *  newFirstName -> user's entered new first name
     *  newLastName -> user's entered new last name
     *  newAddress -> user's entered new current address
     *  newCity -> user's entered new current city
     *  newPassword -> user's entered new password
     *  newEmail -> user's entered new email
     */
    public Map<String, String> getEditUserAccountInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter a user id: ", "userId"),
                new PromptAndInputNameTuple("Enter your new first name: ", "newFirstName"),
                new PromptAndInputNameTuple("Enter your new last name: ", "newLastName"),
                new PromptAndInputNameTuple("Enter your new current address: ", "newAddress"),
                new PromptAndInputNameTuple("Enter your new current city: ", "newCity"),
                new PromptAndInputNameTuple("Enter your new password: ", "newPassword"),
                new PromptAndInputNameTuple("Enter your new email: ", "newEmail")
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * fetchUserProfile command. The mapping is of the form:
     *
     *  userId -> user's entered user id
     */
    public Map<String, String> getFetchUserProfileInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter a user id: ", "userId")
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * editUserProfile command. The mapping is of the form:
     *
     *  userId -> user's entered user id
     *  newBiography -> user's entered new biography
     *  newPhoneNumber -> user's entered new phone number
     *  newInstagram -> user's entered new Instagram
     *  newFacebook -> user's entered new Facebook
     */
    public Map<String, String> getEditUserProfileInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter a user id: ", "userId"),
                new PromptAndInputNameTuple("Enter your new biography: ", "newBiography"),
                new PromptAndInputNameTuple("Enter your new phone number: ", "newPhoneNumber"),
                new PromptAndInputNameTuple("Enter your new Instagram: ", "newInstagram"),
                new PromptAndInputNameTuple("Enter your new Facebook: ", "newFacebook")
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * createPet command. The mapping is of the form:
     *
     *  userId -> id of user this pet belongs to
     *  name -> user's entered pet's name
     *  age -> user's entered pet's age
     *  breed -> user's entered pet's breed
     *  biography -> user's entered pet's biography
     */
    public Map<String, String> getCreatePetInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter the owner's user id: ", "userId"),
                new PromptAndInputNameTuple("Enter your pet's name: ", "name"),
                new PromptAndInputNameTuple("Enter your pet's age: ", "age"),
                new PromptAndInputNameTuple("Enter your pet's breed: ", "breed"),
                new PromptAndInputNameTuple("Enter your pet's biography: ", "biography"),
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * fetchPetProfile command. The mapping is of the form:
     *
     *  petId -> user's entered pet id
     */
    public Map<String, String> getFetchPetProfileInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter a pet id: ", "petId")
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * editPet command. The mapping is of the form:
     *
     *  petId -> user's entered pet id
     *  newName -> user's entered pet's new name
     *  newAge -> user's entered pet's new age
     *  newBreed -> user's entered pet's new breed
     *  newBiography -> user's entered pet's new biography
     */
    public Map<String, String> getEditPetInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter your pet's id: ", "petId"),
                new PromptAndInputNameTuple("Enter your pet's new name: ", "newName"),
                new PromptAndInputNameTuple("Enter your pet's new age: ", "newAge"),
                new PromptAndInputNameTuple("Enter your pet's new breed: ", "newBreed"),
                new PromptAndInputNameTuple("Enter your pet's new biography: ", "newBiography"),

        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Return a mapping containing the necessary inputs for the
     * swipePets command. The mapping is of the form:
     *
     *  pet1id -> pet1's id
     *  pet2id -> pet2's id
     */
    public Map<String, String> getPetSwiperInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter pet1's id: ", "pet1Id"),
                new PromptAndInputNameTuple("Enter pet2's id: ", "pet2Id"),
        };

        return getCommandInputs(inputPrompts);
    }

    /**
     * Given a string representation of a command name, if a corresponding
     * command exists, gather user inputs and run the command.
     *
     * @return the output of the command
     */
    private String selectAndRun(String command) {
        Map<String, String> inputs;
        switch (command) {
            case "createUser":
                inputs = getCreateUserAccountInputs();
                return gateway.createUser(inputs.get("firstName"), inputs.get("lastName"),
                        inputs.get("currentAddress"), inputs.get("currentCity"), inputs.get("password"),
                        inputs.get("email"));

            case "fetchUserAccount":
                inputs = getFetchUserAccountInputs();
                return gateway.fetchUserAccount(inputs.get("userId"));

            case "editUserAccount":
                inputs = getEditUserAccountInputs();
                return gateway.editUserAccount(inputs.get("userId"), inputs.get("newFirstName"),
                        inputs.get("newLastName"), inputs.get("newAddress"), inputs.get("newCity"),
                        inputs.get("newPassword"), inputs.get("newEmail"));

            case "fetchUserProfile":
                inputs = getFetchUserProfileInputs();
                return gateway.fetchUserProfile(inputs.get("userId"));

            case "editUserProfile":
                inputs = getEditUserProfileInputs();
                return gateway.editUserProfile(inputs.get("userId"), inputs.get("newBiography"),
                        inputs.get("newPhoneNumber"), inputs.get("newInstagram"), inputs.get("newFacebook"));

            case "createPet":
                inputs = getCreatePetInputs();
                return gateway.createPet(inputs.get("userId"), inputs.get("name"),
                        Integer.parseInt(inputs.get("age")), inputs.get("breed"), inputs.get("biography"));

            case "swipePets":
                inputs = getPetSwiperInputs();
                return gateway.swipePets(Integer.parseInt(inputs.get("pet1Id")), Integer.parseInt(inputs.get("pet2Id")));

            case "unswipePets":
                inputs = getPetSwiperInputs();
                return gateway.unswipePets(Integer.parseInt(inputs.get("pet1Id")), Integer.parseInt(inputs.get("pet2Id")));

            case "matchPets":
                inputs = getPetSwiperInputs();
                return gateway.matchPets(Integer.parseInt(inputs.get("pet1Id")), Integer.parseInt(inputs.get("pet2Id")));

            case "fetchPetProfile":
                inputs = getFetchPetProfileInputs();
                return gateway.fetchPetProfile(inputs.get("petId"));

            case "editPet":
                inputs = getEditPetInputs();
                return gateway.editPet(inputs.get("petId"), inputs.get("newName"),
                        Integer.parseInt(inputs.get("newAge")), inputs.get("newBreed"), inputs.get("newBiography"));

            default:
                return "Command not found. Choose from createUsers, fetchUsers, editUsers, " +
                        "createPets, fetchPets, editPets and exit.";
        }
    }

    @Autowired
    public UserRepository userRepository;

    /**
     * Run the command line application
     */
    @Override
    public void run(String... args) {
        logger.info("Starting command executor");
        boolean isRunning = true;

        while (isRunning) {
            ioSystem.showOutput("Enter either createUser, fetchUserAccount, editUserAccount, fetchUserProfile, " +
                    "editUserProfile, createPet, fetchPetProfile, editPet, swipePets, unswipePets, matchPets, or exit.");
            String command = ioSystem.getInput();

            if (command.equals("exit")){
                isRunning = false;
            }else{
                ioSystem.showOutput(selectAndRun(command));
            }
        }
    }
}
