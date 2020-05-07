package com.rzdp.fortressauthserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT_NAME = "fortress";
    private static final String CLIENT_SECRET = "s3cr3t";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    private static final String SCOPE_WEB_CLIENT = "webclient";
    private static final String SCOPE_MOBILE_CLIENT = "mobileclient";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_NAME)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_REFRESH_TOKEN,
                        GRANT_TYPE_PASSWORD, GRANT_TYPE_CLIENT_CREDENTIALS)
                .scopes(SCOPE_WEB_CLIENT, SCOPE_MOBILE_CLIENT);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
