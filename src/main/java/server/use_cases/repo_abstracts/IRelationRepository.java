package server.use_cases.repo_abstracts;

public interface IRelationRepository {

    /**
     * Add a relationship between two ids
     * @param fromId
     * @param toId
     * @param relationType
     */
    public void addRelation(int fromId, int toId, String relationType);

    /**
     * Remove a relationship between two ids
     * @param fromId
     * @param toId
     * @param relationType
     */
    public void removeRelation(int fromId, int toId, String relationType);

    /**
     * Return whether fromId has a relation to toId with type relationType
     * @param fromId
     * @param toId
     * @param relationType
     * @return whether fromId has a relation to toId with type relationType
     */
    public boolean hasRelation(int fromId, int toId, String relationType);

}