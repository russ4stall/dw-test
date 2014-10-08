package com.github.russ4stall.dw.auth;


import com.github.russ4stall.dw.api.Name;
import com.github.russ4stall.dw.api.User;
import com.github.russ4stall.dw.core.UserGroup;
import com.github.russ4stall.dw.jdbi.NameDao;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * @author Russ Forstall
 */
public class DWAuthenticator implements Authenticator<String, Name> {
    AuthService authService;

    public DWAuthenticator(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Optional<Name> authenticate(String s) throws AuthenticationException {
        if (authService.containsKey(s)) {
            return Optional.of(authService.get(s));
        }
        return Optional.absent();
    }
}
