package com.marco.identity.domain.identity.model;

import java.util.List;

public class User {

    private Long id;
    private String userName;
    private String password;
    private List<Role> roles;
}
