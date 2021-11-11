package server.drivers.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import server.ServerApplication;

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
     *  home address -> user's entered home address
     *  password -> users' entered password.
     *  email -> user's entered email
     */
    public Map<String, String> getCreateUserAccountInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter your first name: ", "firstName"),
                new PromptAndInputNameTuple("Enter your last name: ", "lastName"),
                new PromptAndInputNameTuple("Enter your home address: ", "homeAddress"),
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
     * createPet command. The mapping is of the form:
     *
     *  name -> user's entered pet's name
     *  age -> user's entered pet's age
     *  breed -> user's entered pet's breed
     *  biography -> user's entered pet's biography
     */
    public Map<String, String> getCreatePetInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
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
     *  newBiography -> user's entered pet's newbiography
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
                        inputs.get("homeAddress"), inputs.get("password"), inputs.get("email"));

            case "fetchUserAccount":
                inputs = getFetchUserAccountInputs();
                return gateway.fetchUserAccount(inputs.get("userId"));

            case "createPet":
                inputs = getCreatePetInputs();
                return gateway.createPet(inputs.get("name"), Integer.parseInt(inputs.get("age")), inputs.get("breed"),
                        inputs.get("biography"));

            case "fetchPetProfile":
                inputs = getFetchPetProfileInputs();
                return gateway.fetchPetProfile(inputs.get("petId"));

            case "editPet":
                inputs = getEditPetInputs();
                return gateway.editPet(inputs.get("petId"), inputs.get("newName"),
                        Integer.parseInt(inputs.get("newAge")), inputs.get("newBreed"), inputs.get("newBiography"));

            default:
                return "Command not found. Choose from createUsers, fetchUsers, " +
                        "createPets, fetchPets, editPets and exit.";
        }
    }

    /**
     * Run the command line application
     */
    @Override
    public void run(String... args) {
        logger.info("Starting command executor");
        boolean isRunning = true;

        while (isRunning) {
            ioSystem.showOutput("Enter either createUser, fetchUserAccount, " +
                    "createPet, fetchPetProfile, editPet or exit.");
            String command = ioSystem.getInput();

            if (command.equals("exit")){
                isRunning = false;
            }else{
                ioSystem.showOutput(selectAndRun(command));
            }
        }
    }
}
