package com.company.authservice.repository;

import com.company.authservice.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

   @Modifying
   @Query(
           "DELETE FROM ProfileEntity profile " +
           "WHERE profile.user.username LIKE LOWER(:username) "
   )
   Optional<ProfileEntity> deleteByUser_UsernameLowerCase(String username);

   Boolean existsByUser_Username(String username);

}

