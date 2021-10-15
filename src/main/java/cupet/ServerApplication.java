package cupet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Class that holds all the dependencies (driver classes, controllers, use cases, etc.)
 * that are used in the Spring application as beans.
 */

@Configuration
class BeanHolder {
    @Bean
    UserCreatorInputBoundary userCreatorBean() {
        return new UserCreator();
    }
    @Bean
    IJSONPresenter jsonPresenterBean() {
        return new JSONPresenter();
    }
    @Bean
    IUserController userControllerBean() {
        return new UserController(userCreatorBean(), jsonPresenterBean());
    }
}


@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
