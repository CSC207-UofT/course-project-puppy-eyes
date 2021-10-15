package server.drivers.cmd;

public interface IOSystem {
    String getInput();
    String getInput(String prompt);
    void showOutput(String output);
}