package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.RelationDatabaseEntity;
import server.use_cases.repo_abstracts.IRelationRepository;

import java.util.Optional;

@Repository
public class RelationRepository implements IRelationRepository {

    private final JpaRelationRepository repository;

    public RelationRepository(JpaRelationRepository repository) {
        this.repository = repository;
    }

    /**
     * Add a relationship between two ids
     * @param fromId
     * @param toId
     * @param relationType
     */
    @Override
    public void addRelation(int fromId, int toId, String relationType) {
        RelationDatabaseEntity relation = new RelationDatabaseEntity(fromId, toId, relationType);
        this.repository.save(relation);
    }

    /**
     * Remove a relationship between two ids
     * @param fromId
     * @param toId
     * @param relationType
     */
    @Override
    public void removeRelation(int fromId, int toId, String relationType) {
        Optional<RelationDatabaseEntity> relation = Optional.ofNullable(this.repository.
                findRelationDatabaseEntityByFromIdAndToId(fromId, toId));

        if (!relation.isPresent()) {
            return;
        }

        this.repository.delete(relation.get());
    }

    /**
     * Return whether fromId has a relation to toId with type relationType
     * @param fromId
     * @param toId
     * @param relationType
     * @return whether fromId has a relation to toId with type relationType
     */
    @Override
    public boolean hasRelation(int fromId, int toId, String relationType) {
        return this.repository.existsByFromIdAndToIdAndRelationType(fromId, toId, relationType);
    }

//    /**
//     * Return
//     * @param fromId
//     * @param relationType
//     * @return
//     */
//    @Override
//    public List<String> getRelations(int fromId, String relationType) {
//
//    }

}
