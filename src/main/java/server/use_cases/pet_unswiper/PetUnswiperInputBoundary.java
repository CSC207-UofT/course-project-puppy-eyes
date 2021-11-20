package server.use_cases.pet_unswiper;

import server.use_cases.ResponseModel;

public interface PetUnswiperInputBoundary {

    public ResponseModel unswipePets(PetUnswiperRequestModel request);

}
