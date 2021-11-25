package server.drivers.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import server.drivers.IJwtService;
import server.drivers.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class serves to intercept all HTTP requests that require authorization by
 * checking if the Authorization header in the request header contains a valid JWT.
 */
public class AuthFilter extends OncePerRequestFilter {

    private final IJwtService jwtService;

    public AuthFilter(IJwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // Grab the Authorization header from the HTTP request
        String authHeader = request.getHeader("Authorization");

        // If header does not exist or is in wrong format, return
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "You are not authorized to make this request.");
            return;
        }

        String token = authHeader.substring(7);
        String userId = jwtService.extractSubject(token);

        // If the JWT was not validated
        if (!jwtService.validateToken(token, userId)) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "You are not authorized to make this request.");
            return;
        }

        HttpServletRequestWrapper modifiedRequest = new HttpServletRequestWrapper((HttpServletRequest) request) {
            public String getHeader(String name) {
                // Modify the original request by appending the userId to the list of headers
                if (name.equals("userId")) {
                    return userId;
                }

                return super.getHeader(name);
            }
        };

        chain.doFilter(modifiedRequest, response);
    }

}
