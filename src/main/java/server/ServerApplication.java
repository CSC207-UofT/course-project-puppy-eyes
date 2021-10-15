package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.controllers.IJSONPresenter;
import server.controllers.IUserController;
import server.controllers.JSONPresenter;
import server.controllers.UserController;
import server.drivers.repository.UserRepository;
import server.use_cases.UserAccountFetcher;
import server.use_cases.UserAccountFetcherInputBoundary;
import server.use_cases.UserCreator;
import server.use_cases.UserCreatorInputBoundary;

/**
 * Class that holds all the dependencies (driver classes, controllers, use cases, etc.)
 * that are used in the Spring application as beans.
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
}


@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
