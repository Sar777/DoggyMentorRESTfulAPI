package io.github.doggymentor.repository.oauth;


import io.github.doggymentor.domain.oauth.OAuth2AuthenticationRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the OAuth2AuthenticationRefreshToken entity.
 */
public interface OAuth2RefreshTokenRepository extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {

    OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);
}