package cupet.driver.repository;

import cupet.driver.dbEntities.UserDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDatabaseEntity, Integer> {

}