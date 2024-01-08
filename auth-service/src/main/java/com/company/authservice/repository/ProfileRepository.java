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

   @Query("SELECT profile FROM ProfileEntity profile " +
           "WHERE profile.user.username " +
           "LIKE LOWER(:username) "
   )
   Optional<ProfileEntity> findByUser_Username(
           @Param("username") String username
  );

   @Modifying
   @Query("DELETE FROM ProfileEntity profile " +
           "WHERE profile.user.username " +
           "LIKE LOWER(:username) "
   )
   void deleteByUser_Username(
           @Param("username") String username
   );

   @Query("SELECT profile.user.username FROM ProfileEntity profile " +
           "WHERE EXISTS (SELECT user FROM UserEntity user " +
           "WHERE user.username " +
           "LIKE LOWER(:username) )"
   )
   Boolean existsByUser_Username(String username);

}

