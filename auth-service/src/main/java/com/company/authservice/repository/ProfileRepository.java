package com.company.authservice.repository;

import com.company.authservice.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

   Optional<ProfileEntity> findByUser_Username(String username);

   void deleteByUser_Username(String username);

   Boolean existsByUser_Username(String username);

}

