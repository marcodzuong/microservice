package com.marco.identity.service;

import com.marco.identity.entities.Authority;
import com.marco.identity.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository repository;

    @Autowired
    public AuthorityService(AuthorityRepository repository){
        this.repository = repository;
    }

    public List<Authority> findAll() {
        return repository.findAll();
    }



}
