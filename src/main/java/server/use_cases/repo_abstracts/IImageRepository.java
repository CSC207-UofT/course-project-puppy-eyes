package server.use_cases.repo_abstracts;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all images.
 */
public interface IImageRepository {

    /**
     * Return the URL storing the given user's profile picture
     *
     * @param userId the id of the given user
     * @return a URL
     */
    String fetchUserProfileImageLink(int userId);

    /**
     * Return the URL storing the given pet's profile picture
     *
     * @param petId the id of the given pet
     * @return a URL
     */
    String fetchPetProfileImageLink(int petId);

    /**
     * Sets this user's profile image to the following URL
     *
     * @param userId  the id of the user
     * @param assetId the id of the asset
     * @param url     the URL of the image to be set as the user's profile image
     * @return true if success, else false
     */
    boolean setUserProfileImage(int userId, String assetId, String url);

    /**
     * Sets this pet's profile image to the following URL
     *
     * @param petId   the id of the pet
     * @param assetId the id of the asset
     * @param url     the URL of the image to be set as the pet's profile image
     * @return true if success, else false
     */
    boolean setPetProfileImage(int petId, String assetId, String url);

}
