package com.github.russ4stall.dw.api;

import io.dropwizard.auth.basic.BasicCredentials;

/**
 * @author Russ Forstall
 */
public class User extends BasicCredentials {

    public User(String username, String password) {
        super(username, password);
    }


}
