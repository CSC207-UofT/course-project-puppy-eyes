package server.use_cases.repo_abstracts;

import java.util.List;

/**
 * An interface defining an access point from the program
 * to a repository storing the information of all images.
 */
public interface IImageRepository {

    /**
     * Return the URL storing the given user's profile picture
     * @param userId
     * @return a URL
     */
    public String fetchUserProfileImageLink(int userId);

    /**
     * Return the URL storing the given pet's profile picture
     * @param petId
     * @return a URL
     */
    public String fetchPetProfileImageLink(int petId);

    /**
     * Sets this user's profile image to the following URL
     * @param userId
     * @param assetId
     * @param url
     * @return true if success, else false
     */
    public boolean setUserProfileImage(int userId, String assetId, String url);

    /**
     * Sets this pet's profile image to the following URL
     * @param petId
     * @param assetId
     * @param url
     * @return true if success, else false
     */
    public boolean setPetProfileImage(int petId, String assetId, String url);

}
