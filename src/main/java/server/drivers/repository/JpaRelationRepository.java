package server.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.RelationDatabaseEntity;

import java.util.List;

@Repository
public interface JpaRelationRepository extends JpaRepository<RelationDatabaseEntity, Integer> {

    public RelationDatabaseEntity findRelationDatabaseEntityByFromIdAndToId(int fromId, int toId);

    public boolean existsByFromIdAndToIdAndRelationType(int fromId, int toId, String relationType);

    public List<RelationDatabaseEntity> findAllByFromIdAndRelationType(int fromId, String relationType);

}