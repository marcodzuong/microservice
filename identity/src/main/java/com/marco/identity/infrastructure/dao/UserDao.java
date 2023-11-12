package com.marco.identity.infrastructure.dao;

import com.marco.identity.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByUserName(String login);
    Optional<UserEntity> findOneByUserName(String login);

}
