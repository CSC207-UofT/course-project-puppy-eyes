package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public class PetProfileValidator implements PetProfileValidatorInputBoundary {

    public ResponseModel validateProfile(PetProfileValidatorRequestModel request) {
        if (request.getName().length() < 2) {
            return new ResponseModel(false, "Please enter a name of at least 2 characters.");
        }

        if (Integer.parseInt(request.getAge()) < 0) {
            return new ResponseModel(false, "Please enter an age of at least 0.");
        }

        if (request.getBreed().length() <= 0) {
            return new ResponseModel(false, "Please enter a breed.");
        }

        return new ResponseModel(true, "All details are valid.");
    }

}