package com.company.authservice.repository;

import com.company.authservice.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> searchByUsernameOrEmailIgnoreCase(
            Pageable pageable,
            String username,
            String email
    );

    Optional<UserEntity> findByUsername(String username);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            value = "user-graph-entity-with-profile"
    )
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    Optional<UserEntity> deleteByUsernameIgnoreCase(String username);

    Boolean existsByUsernameIgnoreCase(String username);

}
