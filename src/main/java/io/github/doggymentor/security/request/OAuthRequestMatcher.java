package io.github.doggymentor.security.request;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class OAuthRequestMatcher implements RequestMatcher {

    public boolean matches(final HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
        boolean haveAccessToken = request.getParameter("access_token") != null;
        return haveOauth2Token || haveAccessToken;
    }
}
