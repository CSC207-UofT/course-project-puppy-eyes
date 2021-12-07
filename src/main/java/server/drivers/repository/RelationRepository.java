package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.RelationDatabaseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class RelationRepository {

    private final JpaRelationRepository repository;

    public RelationRepository(JpaRelationRepository repository) {
        this.repository = repository;
    }

    public JpaRelationRepository getRepository() {
        return this.repository;
    }

    public Optional<RelationDatabaseEntity> getRelation(int fromId, int toId, String relationType) {
        return repository.findRelationDatabaseEntityByFromIdAndToIdAndRelationType(fromId, toId, relationType);
    }

}
