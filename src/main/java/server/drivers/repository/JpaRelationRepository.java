package server.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.RelationDatabaseEntity;

@Repository
public interface JpaRelationRepository extends JpaRepository<RelationDatabaseEntity, Integer> {

    public RelationDatabaseEntity findRelationDatabaseEntityByFromIdAndToId(int fromId, int toId);

    public boolean existsByFromIdAndToIdAndRelationType(int fromId, int toId, String relationType);

}