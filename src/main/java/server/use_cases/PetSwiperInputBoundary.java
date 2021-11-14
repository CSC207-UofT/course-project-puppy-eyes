package server.use_cases;

import server.use_cases.repo_abstracts.ResponseModel;

public interface PetSwiperInputBoundary {

    public ResponseModel swipe(PetSwiperRequestModel request);

}