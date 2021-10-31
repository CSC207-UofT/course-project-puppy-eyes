package server.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.PetDatabaseEntity;

@Repository
public interface JpaPetRepository extends JpaRepository<PetDatabaseEntity, Integer> {
}
