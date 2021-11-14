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
import server.drivers.repository.RelationRepository;
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
    UserAccountEditorInputBoundary userAccountEditorBean(UserRepository userRepository) {
        return new UserAccountEditor(userRepository);
    }

    @Autowired
    @Bean
    UserProfileFetcherInputBoundary userProfileFetcherBean(UserRepository userRepository) {
        return new UserProfileFetcher(userRepository);
    }

    @Autowired
    @Bean
    UserProfileEditorInputBoundary userProfileEditorBean(UserRepository userRepository) {
        return new UserProfileEditor(userRepository);
    }

    @Autowired
    @Bean
    PetCreatorInputBoundary petCreatorBean(PetRepository petRepository, UserRepository userRepository) {
        return new PetCreator(petRepository, userRepository);
    }

    @Autowired
    @Bean
    PetSwiperInputBoundary petSwiperBean(RelationRepository relationRepository, PetRepository petRepository) {
        return new PetSwiper(relationRepository, petRepository);
    }

    @Autowired
    @Bean
    PetUnswiperInputBoundary petUnswiperBean(RelationRepository relationRepository, PetRepository petRepository) {
        return new PetUnswiper(relationRepository, petRepository);
    }

    @Autowired
    @Bean
    PetRejectorInputBoundary petRejectorBean(RelationRepository relationRepository, PetRepository petRepository) {
        return new PetRejector(relationRepository, petRepository);
    }

    @Autowired
    @Bean
    PetProfileFetcherInputBoundary petProfileFetcherBean(PetRepository petRepository) {
        return new PetProfileFetcher(petRepository);
    }

    @Autowired
    @Bean
    PetSwipesFetcherInputBoundary petSwipesFetcherBean(PetRepository petRepository) {
        return new PetSwipesFetcher(petRepository);
    }

    @Autowired
    @Bean
    UserPetsFetcherInputBoundary userPetsFetcherBean(UserRepository userRepository) {
        return new UserPetsFetcher(userRepository);
    }

    @Autowired
    @Bean
    PetMatchesFetcherInputBoundary petMatchesFetcherBean(PetRepository petRepository) {
        return new PetMatchesFetcher(petRepository);
    }

    @Autowired
    @Bean
    PetEditorInputBoundary petEditorBean(PetRepository petRepository) {
        return new PetEditor(petRepository);
    }

    @Autowired
    @Bean
    SessionTokenGeneratorInputBoundary sessionTokenGeneratorBean(UserRepository userRepository) {
        return new SessionTokenGenerator(userRepository, jwtServiceBean());
    }

    // Controllers
    @Autowired
    @Bean
    IUserController userControllerBean(UserRepository userRepository) {
        return new UserController(
                userCreatorBean(userRepository),
                userAccountFetcherBean(userRepository),
                userAccountEditorBean(userRepository),
                userProfileFetcherBean(userRepository),
                userProfileEditorBean(userRepository),
                userPetsFetcherBean(userRepository),
                jsonPresenterBean());
    }

    @Autowired
    @Bean
    IPetController petControllerBean(RelationRepository relationRepository, PetRepository petRepository, UserRepository userRepository) {
        return new PetController(
                petCreatorBean(petRepository, userRepository),
                petSwiperBean(relationRepository, petRepository),
                petProfileFetcherBean(petRepository),
                petEditorBean(petRepository),
                petRejectorBean(relationRepository, petRepository),
                petUnswiperBean(relationRepository, petRepository),
                petSwipesFetcherBean(petRepository),
                petMatchesFetcherBean(petRepository),
                jsonPresenterBean()
        );
    }

    @Autowired
    @Bean
    ISessionController sessionControllerBean(UserRepository userRepository) {
        return new SessionController(sessionTokenGeneratorBean(userRepository), jsonPresenterBean());
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
    GeocoderService geocoderServiceBean() {
        return new GeocoderService(new RestTemplate());
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> authBean = new FilterRegistrationBean<>();

        authBean.setFilter(new AuthFilter(jwtServiceBean()));
        authBean.addUrlPatterns("/authtest");
        authBean.addUrlPatterns("/pets/*");
        authBean.addUrlPatterns("/users/create");
        authBean.addUrlPatterns("/users/account");
        authBean.addUrlPatterns("/users/editaccount");
        authBean.addUrlPatterns("/users/editprofile");

        return authBean;
    }
}

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
