package server.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.ImageDatabaseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageDatabaseEntity, String> {

    public Optional<ImageDatabaseEntity> findByOwnerId(int ownerId);

    public List<ImageDatabaseEntity> findAllByOwnerId(int ownerId);

    public Optional<ImageDatabaseEntity> findByOwnerIdAndType(int ownerId, String type);

}
