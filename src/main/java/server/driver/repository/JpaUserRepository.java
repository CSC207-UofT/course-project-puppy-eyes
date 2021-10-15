package server.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.driver.dbEntities.UserDatabaseEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDatabaseEntity, Integer> {

}