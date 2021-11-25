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
     * Return a list of the URLs that store all the images on this pet's image gallery
     * @param petId
     * @return a list of URLs
     */
    public List<String> fetchPetImagesLink(int petId);

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

    /**
     * Add the image in the following URL to the pet's image gallery
     * @param petId
     * @param assetId
     * @param url
     * @return true if success, else false
     */
    public boolean addPetImage(int petId, String assetId, String url);

    /**
     * Delete the image with the given ID from the remote server
     * @param petId
     * @param assetId
     * @return true if success, else false
     */
    public boolean deletePetImage(int petId, String assetId);

}
