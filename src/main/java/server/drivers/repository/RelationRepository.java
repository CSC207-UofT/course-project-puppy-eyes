package server.drivers.repository;

import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.RelationDatabaseEntity;

import java.util.List;

@Repository
public class RelationRepository {

    private final JpaRelationRepository repository;

    public RelationRepository(JpaRelationRepository repository) {
        this.repository = repository;
    }

    public JpaRelationRepository getRepository() {
        return this.repository;
    }

    public List<RelationDatabaseEntity> getRelations(int fromId, String relationType) {
        return repository.findAllByFromIdAndRelationType(fromId, relationType);
    }

}
