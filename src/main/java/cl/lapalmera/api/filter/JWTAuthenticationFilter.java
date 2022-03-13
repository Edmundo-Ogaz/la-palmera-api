package cl.lapalmera.api.filter;

import cl.lapalmera.api.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static cl.lapalmera.api.constant.SecurityConstants.EXPIRATION_TIME;
import static cl.lapalmera.api.constant.SecurityConstants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/services/controller/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        System.out.println("JWTAuthenticationFilter attemptAuthentication");
        try {
            UserModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserModel.class);

            /*if (!user || !pwd) {
            return res.status(400).json({
                    error: true,
                    message: "Username or Password is required."
    });
        }*/

        /*if (user !== userData.username || pwd !== userData.password) {
        return res.status(401).json({
                error: true,
                message: "Username or Password is wrong."
    });
    }*/

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        System.out.println("JWTAuthenticationFilter successfulAuthentication");
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        String username = ((User) auth.getPrincipal()).getUsername();

        JSONObject user = new JSONObject();
        user.put("userId", 1);
        user.put("name", "test");
        user.put("username", username);
        user.put("isAdmin", true);

        String body = ((User) auth.getPrincipal()).getUsername() + " " + token;

        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("token", token);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(json.toString());
        res.getWriter().flush();
    }
}
