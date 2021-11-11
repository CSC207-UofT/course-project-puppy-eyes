package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import server.controllers.*;
import server.drivers.GeocoderService;
import server.drivers.http.AuthFilter;
import server.drivers.JwtService;
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
    // Use Cases
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
    SessionTokenGeneratorInputBoundary sessionTokenGeneratorBean(UserRepository userRepository) {
        return new SessionTokenGenerator(userRepository, jwtServiceBean());
    }

    // Controllers
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

    @Autowired
    @Bean
    ISessionController sessionControllerBean(UserRepository userRepository) {
        return new SessionController(sessionTokenGeneratorBean(userRepository));
    }

    // Utils/Services
    @Bean
    IOSystem ioSystemBean() {
        return new CmdLineIOSystem();
    }

    @Bean
    IJSONPresenter jsonPresenterBean() {
        return new JSONPresenter();
    }

    @Bean
    JwtService jwtServiceBean() {
        return new JwtService();
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> authBean = new FilterRegistrationBean<>();

        authBean.setFilter(new AuthFilter(jwtServiceBean()));
        authBean.addUrlPatterns("/authtest");

        return authBean;
    }
}

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
