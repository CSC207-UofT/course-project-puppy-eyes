package server.drivers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.drivers.dbEntities.UserDatabaseEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDatabaseEntity, Integer> {

    public UserDatabaseEntity findByContactInfo_email(String email);

}