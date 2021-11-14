package server.drivers.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import server.drivers.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class serves to intercept all HTTP requests that require authorization by
 * checking if the Authorization header in the request header contains a valid JWT.
 */
public class AuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public AuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Grab the Authorization header from the HTTP request
        String authHeader = request.getHeader("Authorization");

        // If header does not exist or is in wrong format, return
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        String token = jwtService.getTokenFromHeader(authHeader);
        String userId = jwtService.extractUserId(token);

        // If the JWT was not validated
        if (!jwtService.validateToken(token, userId)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }

        HttpServletRequestWrapper modifiedRequest = new HttpServletRequestWrapper((HttpServletRequest) request) {
            public String getHeader(String name) {
                // Modify the original request by appending the userId to the list of headers
                if (name.equals("userId")) {
                    return userId;
                }

                return super.getHeader(name);
            }
        };

        chain.doFilter(modifiedRequest, response);
    }

    /**
     * Represents an HTTP request body for the "/pets/edit" POST route.
     */
    public static class EditPetRequestBody {
        private final String petId;
        private final String newName;
        private final int newAge;
        private final String newBreed;
        private final String newBiography;

        public EditPetRequestBody(String petId, String newName, int newAge, String newBreed, String newBiography) {
            this.petId = petId;
            this.newName = newName;
            this.newAge = newAge;
            this.newBreed = newBreed;
            this.newBiography = newBiography;
        }

        public String getPetId() {
            return petId;
        }

        public String getNewName() {
            return newName;
        }

        public int getNewAge() {
            return newAge;
        }

        public String getNewBreed() {
            return newBreed;
        }

        public String getNewBiography() {
            return newBiography;
        }
    }

    /**
     * Represents an HTTP request body for the "/users/profile-edit" POST route.
     */
    public static class EditUserProfileRequestBody {
        private final String userId;
        private final String newBiography;
        private final String newPhoneNumber;
        private final String newInstagram;
        private final String newFacebook;

        public EditUserProfileRequestBody(String userId, String newBiography, String newPhoneNumber, String newInstagram, String newFacebook) {
            this.userId = userId;
            this.newBiography = newBiography;
            this.newPhoneNumber = newPhoneNumber;
            this.newInstagram = newInstagram;
            this.newFacebook = newFacebook;
        }

        public String getUserId() {
            return userId;
        }

        public String getNewBiography() {
            return newBiography;
        }

        public String getNewPhoneNumber() {
            return newPhoneNumber;
        }

        public String getNewInstagram() {
            return newInstagram;
        }

        public String getNewFacebook() {
            return newFacebook;
        }
    }

    /**
     * Represents an HTTP request body for the "/pets/fetchmatches" POST route.
     */
    public static class FetchPetMatchesRequestBody {

        private int petId;

        public FetchPetMatchesRequestBody(int petId) {
            this.petId = petId;
        }

        public int getPetId() {
            return this.petId;
        }

    }

    /**
     * Represents an HTTP request body for the "/pets/profile" GET route.
     */
    public static class FetchPetProfileRequestBody {
        private final String petId;

        public FetchPetProfileRequestBody(String petId) {
            this.petId = petId;
        }

        public String getPetId() {
            return petId;
        }
    }

    /**
     * Represents an HTTP request body for the "/pets/fetchswipes" POST route.
     */
    public static class FetchPetSwipesRequestBody {

        private int petId;

        public FetchPetSwipesRequestBody(int petId) {
            this.petId = petId;
        }

        public int getPetId() {
            return this.petId;
        }

    }

    /**
     * Represents an HTTP request body for the "/users/account" GET route.
     */
    public static class FetchUserAccountRequestBody {
        private final String userId;

        public FetchUserAccountRequestBody(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }
    }

    /**
     * Represents an HTTP request body for the "/users/fetchpets" GET route.
     */
    public static class FetchUserPetsRequestBody {
        private final int userId;

        public FetchUserPetsRequestBody(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }
    }

    /**
     * Represents an HTTP request body for the "/users/profile" GET route.
     */
    public static class FetchUserProfileRequestBody {
        private final String userId;

        public FetchUserProfileRequestBody(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }
    }

    /**
     * Represents an HTTP request body for the "/auth/login" POST route.
     */
    public static class LoginRequestBody {

        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public LoginRequestBody(String email, String password) {
            this.email = email;
            this.password = password;
        }

    }

    /**
     * Represents an HTTP request body for the "/pets/swipe" POST route.
     */
    public static class SwipePetsRequestBody {

        private int pet1Id;
        private int pet2Id;

        public SwipePetsRequestBody(int pet1Id, int pet2Id) {
            this.pet1Id = pet1Id;
            this.pet2Id = pet2Id;
        }

        public int getFirstPetId() {
            return this.pet1Id;
        }

        public int getSecondPetId() {
            return this.pet2Id;
        }

    }

    /**
     * Represents an HTTP request body for the "/pets/reject" POST route.
     */
    public static class RejectPetsRequestBody {

        private int pet1Id;
        private int pet2Id;

        public RejectPetsRequestBody(int pet1Id, int pet2Id) {
            this.pet1Id = pet1Id;
            this.pet2Id = pet2Id;
        }

        public int getFirstPetId() {
            return this.pet1Id;
        }

        public int getSecondPetId() {
            return this.pet2Id;
        }

    }

    /**
     * Represents an HTTP request body for the "/pets/unswipe" POST route.
     */
    public static class UnswipePetsRequestBody {

        private int pet1Id;
        private int pet2Id;

        public UnswipePetsRequestBody(int pet1Id, int pet2Id) {
            this.pet1Id = pet1Id;
            this.pet2Id = pet2Id;
        }

        public int getFirstPetId() {
            return this.pet1Id;
        }

        public int getSecondPetId() {
            return this.pet2Id;
        }

    }
}
