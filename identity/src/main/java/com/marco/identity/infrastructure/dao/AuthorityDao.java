package com.marco.identity.infrastructure.dao;

import com.marco.identity.infrastructure.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao extends JpaRepository<AuthorityEntity,String> {
}
