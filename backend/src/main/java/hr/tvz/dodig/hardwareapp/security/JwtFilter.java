package hr.tvz.dodig.hardwareapp.security;

import hr.tvz.dodig.hardwareapp.security.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        if (!isEndpointAllowingUnauthenticatedAccess(request)) {
            String jwtToken = extractJwtToken(request);

            log.trace("doFilter for endpoint: {} resolved jwt: {}", request.getRequestURI(), jwtToken);

            if (jwtToken != null && !jwtToken.isEmpty()) {

                boolean authenticate = jwtService.authenticate(jwtToken);

                if (!authenticate) {
                    unauthorized(response);
                }

            } else {
                unauthorized(response);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void unauthorized(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(AUTHORIZATION_TOKEN_PREFIX)) {
            return bearerToken.substring(AUTHORIZATION_TOKEN_PREFIX.length());
        }
        return null;
    }

    private boolean isEndpointAllowingUnauthenticatedAccess(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return SecurityConfiguration.UNAUTHENTICATED_ENDPOINTS
                .stream()
                .anyMatch(endpoint -> uri.contains(endpointWithoutWildcard(endpoint)));
    }

    private String endpointWithoutWildcard(String endpoint) {
        return endpoint.replace("**", "");
    }
}
