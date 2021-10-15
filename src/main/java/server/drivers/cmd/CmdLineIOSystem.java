package server.drivers.cmd;

import java.util.Scanner;

/**
 * A class that handles user input and output via System.in and System.out.
 */
public class CmdLineIOSystem implements IOSystem {
    Scanner scanner;

    public CmdLineIOSystem() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        return scanner.next();
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return getInput();
    }

    /**
     * Display output to the command line in a new line.
     */
    @Override
    public void showOutput(String output) {
        System.out.println(output);
    }
}
