package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

import java.util.regex.Pattern;

public class UserAccountValidator implements UserAccountValidatorInputBoundary {

    public ResponseModel validateAccount(UserAccountValidatorRequestModel request) {
        if (request.getFirstName().length() < 2 || request.getLastName().length() < 2) {
            return new ResponseModel(false, "Please enter a name of at least 2 characters.");
        }

        if (request.getPassword().length() < 6) {
            return new ResponseModel(false, "Please enter a password of at least 6 characters.");
        }

        String includesDigitRegex = ".*[0-9].*";

        if (!Pattern.matches(includesDigitRegex, request.getPassword())) {
            return new ResponseModel(false, "Please enter a password with at least 1 digit.");
        }

        String includesOneLowercase = ".*[a-z].*";

        if (!Pattern.matches(includesOneLowercase, request.getPassword())) {
            return new ResponseModel(false, "Please enter a password with at least 1 lowercase letter.");
        }

        String includesOneUppercase = ".*[A-Z].*";

        if (!Pattern.matches(includesOneUppercase, request.getPassword())) {
            return new ResponseModel(false, "Please enter a password with at least 1 uppercase letter.");
        }

        if (request.getCurrentCity().length() < 1) {
            return new ResponseModel(false, "Please enter a city name.");
        }

        if (request.getEmail().length() < 5) {
            return new ResponseModel(false, "Please enter an email of at least 5 characters.");
        }

        return new ResponseModel(true, "All details are valid.");
    }

}
