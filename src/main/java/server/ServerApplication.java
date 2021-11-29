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
import server.drivers.repository.ImageRepository;
import server.drivers.repository.UserRepository;
import server.drivers.repository.PetRepository;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidator;
import server.use_cases.pet_use_cases.pet_action_validator.PetActionValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_creator.PetCreator;
import server.use_cases.pet_use_cases.pet_creator.PetCreatorInputBoundary;
import server.use_cases.pet_use_cases.pet_editor.PetEditor;
import server.use_cases.pet_use_cases.pet_editor.PetEditorInputBoundary;
import server.use_cases.pet_use_cases.pet_gallery_images_fetcher.PetGalleryImagesFetcher;
import server.use_cases.pet_use_cases.pet_gallery_images_fetcher.PetGalleryImagesFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_image_adder.PetImageAdder;
import server.use_cases.pet_use_cases.pet_image_adder.PetImageAdderInputBoundary;
import server.use_cases.pet_use_cases.pet_image_remover.PetImageRemover;
import server.use_cases.pet_use_cases.pet_image_remover.PetImageRemoverInputBoundary;
import server.use_cases.pet_use_cases.pet_matches_fetcher.PetMatchesFetcher;
import server.use_cases.pet_use_cases.pet_matches_fetcher.PetMatchesFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGenerator;
import server.use_cases.pet_use_cases.pet_matches_generator.PetMatchesGeneratorInputBoundary;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcher;
import server.use_cases.pet_use_cases.pet_profile_fetcher.PetProfileFetcherInputBoundary;
import server.use_cases.pet_use_cases.pet_profile_image_changer.PetProfileImageChanger;
import server.use_cases.pet_use_cases.pet_profile_image_changer.PetProfileImageChangerInputBoundary;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidator;
import server.use_cases.pet_use_cases.pet_profile_validator.PetProfileValidatorInputBoundary;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractor;
import server.use_cases.pet_use_cases.pet_interactor.PetInteractorInputBoundary;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcher;
import server.use_cases.pet_use_cases.pet_swipes_fetcher.PetSwipesFetcherInputBoundary;
import server.use_cases.ResponsePresenter;
import server.adapters.UseCaseOutputBoundary;
import server.use_cases.profile_image_fetcher.ProfileImageFetcher;
import server.use_cases.profile_image_fetcher.ProfileImageFetcherInputBoundary;
import server.use_cases.user_use_cases.session_token_generator.SessionTokenGenerator;
import server.use_cases.user_use_cases.session_token_generator.SessionTokenGeneratorInputBoundary;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditor;
import server.use_cases.user_use_cases.user_account_editor.UserAccountEditorInputBoundary;
import server.use_cases.user_use_cases.user_account_fetcher.UserAccountFetcher;
import server.use_cases.user_use_cases.user_account_fetcher.UserAccountFetcherInputBoundary;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidator;
import server.use_cases.user_use_cases.user_account_validator.UserAccountValidatorInputBoundary;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidator;
import server.use_cases.user_use_cases.user_action_validator.UserActionValidatorInputBoundary;
import server.use_cases.user_use_cases.user_creator.UserCreator;
import server.use_cases.user_use_cases.user_creator.UserCreatorInputBoundary;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcher;
import server.use_cases.user_use_cases.user_pets_fetcher.UserPetsFetcherInputBoundary;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditor;
import server.use_cases.user_use_cases.user_profile_editor.UserProfileEditorInputBoundary;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcher;
import server.use_cases.user_use_cases.user_profile_fetcher.UserProfileFetcherInputBoundary;
import server.use_cases.user_use_cases.user_profile_image_changer.UserProfileImageChanger;
import server.use_cases.user_use_cases.user_profile_image_changer.UserProfileImageChangerInputBoundary;

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
    @Bean
    ProfileImageFetcherInputBoundary profileImageFetcherBean(ImageRepository imageRepository, UserRepository userRepository, PetRepository petRepository) {
        return new ProfileImageFetcher(imageRepository, petActionValidatorBean(petRepository), userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean
    PetGalleryImagesFetcherInputBoundary petGalleryImagesFetcher(ImageRepository imageRepository, PetRepository petRepository) {
        return new PetGalleryImagesFetcher(imageRepository, petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    UserActionValidatorInputBoundary userActionValidatorBean(UserRepository userRepository) {
        return new UserActionValidator(userRepository);
    }

    @Autowired
    @Bean
    PetActionValidatorInputBoundary petActionValidatorBean(PetRepository petRepository) {
        return new PetActionValidator(petRepository);
    }

    @Autowired
    @Bean
    UserProfileImageChangerInputBoundary userProfileImageChangerBean(ImageRepository imageRepository, UserRepository userRepository) {
        return new UserProfileImageChanger(imageRepository, imageServiceBean(), userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean()
    UserCreatorInputBoundary userCreatorBean(UserRepository userRepository) {
        return new UserCreator(userRepository, passwordEncryptorBean(), userCredentialsValidatorBean());
    }

    @Autowired
    @Bean
    UserAccountFetcherInputBoundary userAccountFetcherBean(UserRepository userRepository) {
        return new UserAccountFetcher(userRepository, userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean
    UserAccountEditorInputBoundary userAccountEditorBean(UserRepository userRepository) {
        return new UserAccountEditor(userRepository, passwordEncryptorBean(), userCredentialsValidatorBean(), userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean
    UserProfileFetcherInputBoundary userProfileFetcherBean(UserRepository userRepository, PetRepository petRepository) {
        return new UserProfileFetcher(userRepository, petRepository, userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean
    UserProfileEditorInputBoundary userProfileEditorBean(UserRepository userRepository) {
        return new UserProfileEditor(userRepository, userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean
    PetProfileImageChangerInputBoundary petProfileImageChangerBean(ImageRepository imageRepository, PetRepository petRepository) {
        return new PetProfileImageChanger(imageRepository, imageServiceBean(), petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    PetImageAdderInputBoundary petImageAdderBean(ImageRepository imageRepository, PetRepository petRepository) {
        return new PetImageAdder(imageRepository, petRepository, imageServiceBean(), petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    PetImageRemoverInputBoundary petImageRemoverBean(ImageRepository imageRepository, PetRepository petRepository) {
        return new PetImageRemover(imageRepository, petRepository, imageServiceBean(), petActionValidatorBean(petRepository));
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
    PetInteractorInputBoundary petInteractorBean(PetRepository petRepository) {
        return new PetInteractor(petRepository, petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    PetProfileFetcherInputBoundary petProfileFetcherBean(PetRepository petRepository) {
        return new PetProfileFetcher(petRepository, petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    PetSwipesFetcherInputBoundary petSwipesFetcherBean(PetRepository petRepository) {
        return new PetSwipesFetcher(petRepository, petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    UserPetsFetcherInputBoundary userPetsFetcherBean(UserRepository userRepository) {
        return new UserPetsFetcher(userRepository, userActionValidatorBean(userRepository));
    }

    @Autowired
    @Bean
    PetMatchesFetcherInputBoundary petMatchesFetcherBean(PetRepository petRepository) {
        return new PetMatchesFetcher(petRepository, petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    PetMatchesGeneratorInputBoundary petMatchesGeneratorBean(UserRepository userRepository, PetRepository petRepository) {
        return new PetMatchesGenerator(userRepository, petRepository, petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    PetEditorInputBoundary petEditorBean(PetRepository petRepository) {
        return new PetEditor(petRepository, petProfileValidatorBean(), petActionValidatorBean(petRepository));
    }

    @Autowired
    @Bean
    SessionTokenGeneratorInputBoundary sessionTokenGeneratorBean(UserRepository userRepository) {
        return new SessionTokenGenerator(userRepository, jwtServiceBean(), passwordEncryptorBean());
    }

    // Controllers
    @Autowired
    @Bean
    IUserController userControllerBean(UserRepository userRepository, PetRepository petRepository, ImageRepository imageRepository) {
        return new UserController(
                userCreatorBean(userRepository),
                userAccountFetcherBean(userRepository),
                userAccountEditorBean(userRepository),
                userProfileFetcherBean(userRepository, petRepository),
                userProfileEditorBean(userRepository),
                userPetsFetcherBean(userRepository),
                userProfileImageChangerBean(imageRepository, userRepository),
                profileImageFetcherBean(imageRepository, userRepository, petRepository),
                responsePresenterBean()
        );
    }

    @Autowired
    @Bean
    IPetController petControllerBean(PetRepository petRepository, UserRepository userRepository, ImageRepository imageRepository) {
        return new PetController(
                petCreatorBean(petRepository, userRepository),
                petProfileFetcherBean(petRepository),
                petEditorBean(petRepository),
                petInteractorBean(petRepository),
                petSwipesFetcherBean(petRepository),
                petMatchesFetcherBean(petRepository),
                petMatchesGeneratorBean(userRepository, petRepository),
                petProfileImageChangerBean(imageRepository, petRepository),
                petImageAdderBean(imageRepository, petRepository),
                petImageRemoverBean(imageRepository, petRepository),
                profileImageFetcherBean(imageRepository, userRepository, petRepository),
                petGalleryImagesFetcher(imageRepository, petRepository),
                responsePresenterBean()
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

    @Bean(name = "imageService")
    IImageService imageServiceBean() {
        return new CloudinaryService();
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> authBean = new FilterRegistrationBean<>();

        authBean.setFilter(new AuthFilter(jwtServiceBean()));
        authBean.addUrlPatterns("/authtest");
        authBean.addUrlPatterns("/pets/*");
        authBean.addUrlPatterns("/users/profile");
        authBean.addUrlPatterns("/users/account");
        authBean.addUrlPatterns("/users/editaccount");
        authBean.addUrlPatterns("/users/editprofile");
        authBean.addUrlPatterns("/users/setprofileimage");

        return authBean;
    }
}

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
