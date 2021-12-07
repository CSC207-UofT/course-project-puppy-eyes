package server.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.RelationDatabaseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRelationRepository extends JpaRepository<RelationDatabaseEntity, Integer> {

    Optional<RelationDatabaseEntity> findRelationDatabaseEntityByFromIdAndToIdAndRelationType(int fromId, int toId, String relationType);

    List<RelationDatabaseEntity> findAllByFromIdAndRelationType(int fromId, String relationType);

}