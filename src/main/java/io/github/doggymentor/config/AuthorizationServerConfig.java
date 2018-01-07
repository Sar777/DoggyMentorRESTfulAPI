package io.github.doggymentor.config;

import io.github.doggymentor.config.token.MongoDBTokenStore;
import io.github.doggymentor.repository.oauth.OAuth2AccessTokenRepository;
import io.github.doggymentor.repository.oauth.OAuth2RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;
    @Value("${access_token.validity_period:3600}")
    private int accessTokenValiditySeconds = 3600;
    @Value("${refresh_token.validity_period:7200}")
    private int refreshTokenValiditySeconds = 7200;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final OAuth2AccessTokenRepository oAuth2AccessTokenRepository;

    private final OAuth2RefreshTokenRepository oAuth2RefreshTokenRepository;

    @Autowired
    public AuthorizationServerConfig(@Qualifier("authenticationManagerBean") final AuthenticationManager authenticationManager,
                                     final UserDetailsService userDetailsService,
                                     final OAuth2AccessTokenRepository oAuth2AccessTokenRepository,
                                     final OAuth2RefreshTokenRepository oAuth2RefreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.oAuth2AccessTokenRepository = oAuth2AccessTokenRepository;
        this.oAuth2RefreshTokenRepository = oAuth2RefreshTokenRepository;
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("trusted-app")
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .authorities("USER")
                .scopes("read", "write")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .secret("secret");
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .pathMapping("/oauth/authorize", "/api/v1/oauth/authorize")
                .pathMapping("/oauth/token", "/api/v1/oauth/token")
                .pathMapping("/oauth/сheck_token", "/api/v1/oauth/сheck_token");
    }

    @Bean
    public TokenStore tokenStore() {
        return new MongoDBTokenStore(oAuth2AccessTokenRepository, oAuth2RefreshTokenRepository);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices authorizationServerTokenServices
                = new DefaultTokenServices();

        authorizationServerTokenServices.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        authorizationServerTokenServices.setSupportRefreshToken(true);
        authorizationServerTokenServices.setTokenStore(tokenStore());
        authorizationServerTokenServices
                .setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);

        return authorizationServerTokenServices;
    }
}
