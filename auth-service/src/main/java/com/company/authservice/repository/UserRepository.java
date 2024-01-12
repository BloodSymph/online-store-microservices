package com.company.authservice.repository;

import com.company.authservice.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> searchByUsernameIgnoreCase(
            Pageable pageable,
            String username
    );

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            value = "user-graph-entity-with-profile-and-roles"
    )
    @Query(
        "SELECT user FROM UserEntity user " +
        "WHERE user.username " +
        "LIKE LOWER(:username) "
    )
    Optional<UserEntity> getUserDetails(
            @Param("username") String username
    );

    void deleteByUsernameIgnoreCase(String username);

    Boolean existsByUsernameIgnoreCase(String username);

}
