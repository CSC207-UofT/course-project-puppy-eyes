package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.controllers.IJSONPresenter;
import server.controllers.IUserController;
import server.controllers.IPetController;
import server.controllers.JSONPresenter;
import server.controllers.UserController;
import server.controllers.PetController;
import server.drivers.cmd.CmdLineIOSystem;
import server.drivers.cmd.IOSystem;
import server.drivers.repository.UserRepository;
import server.drivers.repository.PetRepository;
import server.use_cases.*;

/**
 * Class that holds all the dependencies used in the application at the moment.
 * (i.e., which controllers, use case classes are used specifically).
 *
 * This is where we inject the implementations of most of our interfaces.
 */

@Configuration
class BeanHolder {

    @Autowired
    @Bean()
    UserCreatorInputBoundary userCreatorBean(UserRepository userRepository) {
        return new UserCreator(userRepository);
    }

    @Autowired
    @Bean
    UserAccountFetcherInputBoundary userAccountFetcherBean(UserRepository userRepository) {
        return new UserAccountFetcher(userRepository);
    }

    @Autowired
    @Bean
    PetCreatorInputBoundary petCreatorBean(PetRepository petRepository) {
        return new PetCreator(petRepository);
    }

    @Bean
    IJSONPresenter jsonPresenterBean() {
        return new JSONPresenter();
    }

    @Autowired
    @Bean
    IUserController userControllerBean(UserRepository userRepository) {
        return new UserController(userCreatorBean(userRepository),
                userAccountFetcherBean(userRepository), jsonPresenterBean());
    }

    @Autowired
    @Bean
    IPetController petControllerBean(PetRepository petRepository) {
        return new PetController(petCreatorBean(petRepository), jsonPresenterBean());
    }

    @Bean
    IOSystem ioSystemBean() {
        return new CmdLineIOSystem();
    }
}


@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
