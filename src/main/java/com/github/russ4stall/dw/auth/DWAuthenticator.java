package com.github.russ4stall.dw.auth;


import com.github.russ4stall.dw.core.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import javax.swing.text.html.Option;

/**
 * @author Russ Forstall
 */
public class DWAuthenticator implements Authenticator<String, User> {
    @Override
    public Optional<User> authenticate(String s) throws AuthenticationException {
        if ("password".equals(s)) {
            return Optional.of(new User("Russ", "password"));
        }
        return Optional.absent();
    }
}
