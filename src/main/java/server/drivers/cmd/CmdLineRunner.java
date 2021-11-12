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
     * createPet command. The mapping is of the form:
     *
     *  userId -> id of user this pet belongs to
     *  name -> user's entered pet's name
     *  age -> user's entered pet's age
     */
    public Map<String, String> getCreatePetInputs() {
        PromptAndInputNameTuple[] inputPrompts = {
                new PromptAndInputNameTuple("Enter the owner's user id: ", "userId"),
                new PromptAndInputNameTuple("Enter your pet's name: ", "name"),
                new PromptAndInputNameTuple("Enter your pet's age: ", "age"),
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
    private String selectAndRun(String command){
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

            case "createPet":
                inputs = getCreatePetInputs();
                return gateway.createPet(Integer.parseInt(inputs.get("userId")), inputs.get("name"),
                        Integer.parseInt(inputs.get("age")));

            case "swipePets":
                inputs = getPetSwiperInputs();
                return gateway.swipePets(Integer.parseInt(inputs.get("pet1Id")), Integer.parseInt(inputs.get("pet2Id")));

            case "unswipePets":
                inputs = getPetSwiperInputs();
                return gateway.unswipePets(Integer.parseInt(inputs.get("pet1Id")), Integer.parseInt(inputs.get("pet2Id")));

            case "matchPets":
                inputs = getPetSwiperInputs();
                return gateway.matchPets(Integer.parseInt(inputs.get("pet1Id")), Integer.parseInt(inputs.get("pet2Id")));

            default:
                return "Command not found. Choose from fetchUsers, createUsers, and exit.";
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
            ioSystem.showOutput("Enter either createUser, fetchUserAccount, createPet. or exit.");
            String command = ioSystem.getInput();

            if (command.equals("exit")){
                isRunning = false;
            }else{
                ioSystem.showOutput(selectAndRun(command));
            }
        }
    }
}
