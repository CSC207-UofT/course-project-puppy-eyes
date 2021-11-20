package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.controllers.*;
import server.drivers.*;
import server.drivers.http.AuthFilter;
import server.drivers.cmd.CmdLineIOSystem;
import server.drivers.cmd.IOSystem;
import server.drivers.repository.UserRepository;
import server.drivers.repository.PetRepository;
import server.use_cases.pet_creator.PetCreator;
import server.use_cases.pet_creator.PetCreatorInputBoundary;
import server.use_cases.pet_editor.PetEditor;
import server.use_cases.pet_editor.PetEditorInputBoundary;
import server.use_cases.pet_matches_fetcher.PetMatchesFetcher;
import server.use_cases.pet_matches_fetcher.PetMatchesFetcherInputBoundary;
import server.use_cases.pet_matches_generator.PetMatchesGenerator;
import server.use_cases.pet_matches_generator.PetMatchesGeneratorInputBoundary;
import server.use_cases.pet_profile_fetcher.PetProfileFetcher;
import server.use_cases.pet_profile_fetcher.PetProfileFetcherInputBoundary;
import server.use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.pet_profile_validator.PetProfileValidatorInputBoundary;
import server.use_cases.pet_rejector.PetRejector;
import server.use_cases.pet_rejector.PetRejectorInputBoundary;
import server.use_cases.pet_swiper.PetSwiper;
import server.use_cases.pet_swiper.PetSwiperInputBoundary;
import server.use_cases.pet_swipes_fetcher.PetSwipesFetcher;
import server.use_cases.pet_swipes_fetcher.PetSwipesFetcherInputBoundary;
import server.use_cases.pet_unswiper.PetUnswiper;
import server.use_cases.pet_unswiper.PetUnswiperInputBoundary;
import server.use_cases.repo_abstracts.ResponsePresenter;
import server.use_cases.repo_abstracts.UseCaseOutputBoundary;
import server.use_cases.session_token_generator.SessionTokenGenerator;
import server.use_cases.session_token_generator.SessionTokenGeneratorInputBoundary;
import server.use_cases.user_account_editor.UserAccountEditor;
import server.use_cases.user_account_editor.UserAccountEditorInputBoundary;
import server.use_cases.user_account_fetcher.UserAccountFetcher;
import server.use_cases.user_account_fetcher.UserAccountFetcherInputBoundary;
import server.use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_account_validator.UserAccountValidatorInputBoundary;
import server.use_cases.user_creator.UserCreator;
import server.use_cases.user_creator.UserCreatorInputBoundary;
import server.use_cases.user_pets_fetcher.UserPetsFetcher;
import server.use_cases.user_pets_fetcher.UserPetsFetcherInputBoundary;
import server.use_cases.user_profile_editor.UserProfileEditor;
import server.use_cases.user_profile_editor.UserProfileEditorInputBoundary;
import server.use_cases.user_profile_fetcher.UserProfileFetcher;
import server.use_cases.user_profile_fetcher.UserProfileFetcherInputBoundary;

/**
 * Class that holds all the dependencies used in the application at the moment.
 * (i.e., which controllers, use case classes are used specifically).
 *
 * This is where we inject the implementations of most of our interfaces.
 */

@Configuration
class BeanHolder {

    // Use Cases
    @Bean
    UserAccountValidatorInputBoundary userCredentialsValidatorBean() {
        return new UserAccountValidator();
    }

    @Autowired
    @Bean()
    UserCreatorInputBoundary userCreatorBean(UserRepository userRepository) {
        return new UserCreator(userRepository, passwordEncryptorBean(), userCredentialsValidatorBean());
    }

    @Autowired
    @Bean
    UserAccountFetcherInputBoundary userAccountFetcherBean(UserRepository userRepository) {
        return new UserAccountFetcher(userRepository);
    }

    @Autowired
    @Bean
    UserAccountEditorInputBoundary userAccountEditorBean(UserRepository userRepository) {
        return new UserAccountEditor(userRepository, passwordEncryptorBean(), userCredentialsValidatorBean());
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

    @Bean
    PetProfileValidatorInputBoundary petProfileValidatorBean() {
        return new PetProfileValidator();
    }

    @Autowired
    @Bean
    PetCreatorInputBoundary petCreatorBean(PetRepository petRepository, UserRepository userRepository) {
        return new PetCreator(petRepository, userRepository, petProfileValidatorBean());
    }

    @Autowired
    @Bean
    PetSwiperInputBoundary petSwiperBean(PetRepository petRepository) {
        return new PetSwiper(petRepository);
    }

    @Autowired
    @Bean
    PetUnswiperInputBoundary petUnswiperBean(PetRepository petRepository) {
        return new PetUnswiper(petRepository);
    }

    @Autowired
    @Bean
    PetRejectorInputBoundary petRejectorBean(PetRepository petRepository) {
        return new PetRejector(petRepository);
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
    PetMatchesGeneratorInputBoundary petMatchesGeneratorBean(UserRepository userRepository, PetRepository petRepository) {
        return new PetMatchesGenerator(userRepository, petRepository);
    }

    @Autowired
    @Bean
    PetEditorInputBoundary petEditorBean(PetRepository petRepository) {
        return new PetEditor(petRepository, petProfileValidatorBean());
    }

    @Autowired
    @Bean
    SessionTokenGeneratorInputBoundary sessionTokenGeneratorBean(UserRepository userRepository) {
        return new SessionTokenGenerator(userRepository, jwtServiceBean(), passwordEncryptorBean());
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
    IPetController petControllerBean(PetRepository petRepository, UserRepository userRepository) {
        return new PetController(
                petCreatorBean(petRepository, userRepository),
                petSwiperBean(petRepository),
                petProfileFetcherBean(petRepository),
                petEditorBean(petRepository),
                petRejectorBean(petRepository),
                petUnswiperBean(petRepository),
                petSwipesFetcherBean(petRepository),
                petMatchesFetcherBean(petRepository),
                petMatchesGeneratorBean(userRepository, petRepository),
                jsonPresenterBean()
        );
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
    UseCaseOutputBoundary responsePresenterBean() {
        return new ResponsePresenter(jsonPresenterBean());
    }

    @Bean
    IJwtService jwtServiceBean() {
        return new JwtService();
    }

    @Bean
    GeocoderService geocoderServiceBean() {
        return new GeocoderService();
    }

    @Bean
    IPasswordEncryptor passwordEncryptorBean() {
        return new BCryptService();
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> authBean = new FilterRegistrationBean<>();

        authBean.setFilter(new AuthFilter(jwtServiceBean()));
        authBean.addUrlPatterns("/authtest");
        authBean.addUrlPatterns("/pets/*");
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
