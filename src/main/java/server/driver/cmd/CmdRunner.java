package server.driver.cmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import server.ServerApplication;
import server.driver.CmdLineGateway;

import java.util.Scanner;

@Component
public class CmdRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    private final CmdLineGateway gateway;

    public CmdRunner(CmdLineGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting command executor");

        Scanner in = new Scanner(System.in);

        // TODO create a Class to deal with commands
        while (true) {
            System.out.println("Please enter a command:");
            String line = in.nextLine();
            String[] cmdArgs = line.trim().split(" ");
            String command = cmdArgs[0];

            if (command.equalsIgnoreCase("fetchusers")) {
                System.out.println(gateway.getAllUsers());
            } else if (command.equalsIgnoreCase("createuser")) {
                // If arguments are invalid
                if (cmdArgs.length - 1 < 5) {
                    System.out.println("createuser requires 5 arguments: createuser <firstName> <lastName> <homeAddress> <password> <email>");
                } else {
                    String firstName = cmdArgs[1];
                    String lastName = cmdArgs[2];
                    String homeAddress = cmdArgs[3];
                    String password = cmdArgs[4];
                    String email = cmdArgs[5];

                    // Let gateway create the user and then print out the JSON response
                    System.out.println(gateway.createUser(firstName, lastName, homeAddress, password, email));
                }
            }
        }
    }

}
