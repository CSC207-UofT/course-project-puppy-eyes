package server.drivers.cmd;

/**
 * A system that handles input and output from and to a user.
 */
public interface IOSystem {
    String getInput();
    String getInput(String prompt);
    void showOutput(String output);
}