package io.github.doggymentor.config.token;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import io.github.doggymentor.domain.common.Authority;
import io.github.doggymentor.domain.user.User;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.*;

/**
 * Converter to deserialize back into an OAuth2Authentication Object made necessary because
 * Spring Mongo can't map clientAuthentication to authorizationRequest.
 */
@ReadingConverter
public class OAuth2AuthenticationReadConverter implements Converter<DBObject, OAuth2Authentication> {

    @Override
    public OAuth2Authentication convert(DBObject source) {
        DBObject storedRequest = (DBObject)source.get("storedRequest");
        OAuth2Request oAuth2Request = new OAuth2Request((Map<String, String>)storedRequest.get("requestParameters"),
                (String)storedRequest.get("clientId"), null, true, new HashSet((List)storedRequest.get("scope")),
                null, null, null, null);

        DBObject userAuthorization = (DBObject)source.get("userAuthentication");
        Object principal = getPrincipalObject(userAuthorization.get("principal"));
        Authentication userAuthentication = new UsernamePasswordAuthenticationToken(principal,
                userAuthorization.get("credentials"), getAuthorities((BasicDBList) userAuthorization.get("authorities")));

        return new OAuth2Authentication(oAuth2Request,  userAuthentication );
    }

    private Object getPrincipalObject(Object principal) {
        if (principal instanceof DBObject) {
            DBObject principalDBObject = (DBObject)principal;

            String id = ((ObjectId) principalDBObject.get("_id")).toString();
            String username = (String) principalDBObject.get("username");
            String email = (String) principalDBObject.get("email");
            String password = (String) principalDBObject.get("password");
            Integer gender = (Integer) principalDBObject.get("gender");

            List<Authority> authorities = new ArrayList<>();

            for (Object role : (BasicDBList)principalDBObject.get("authorities")) {
                authorities.add(Authority.valueOf((String) role));
            }

            boolean enabled = (boolean) principalDBObject.get("isEnabled");
            boolean accountNonExpired = (boolean) principalDBObject.get("accountNonExpired");
            boolean credentialsNonExpired = (boolean) principalDBObject.get("credentialsNonExpired");
            boolean accountNonLocked = (boolean) principalDBObject.get("accountNonLocked");

            return new User(id, authorities, username, email, password, gender, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
        } else {
            return principal;
        }
    }

    private Collection<GrantedAuthority> getAuthorities(BasicDBList basicDBList) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Object role : basicDBList) {
            grantedAuthorities.add(new SimpleGrantedAuthority((String) role));
        }
        return grantedAuthorities;
    }
}