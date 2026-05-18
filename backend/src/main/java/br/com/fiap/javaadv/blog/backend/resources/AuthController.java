package br.com.fiap.javaadv.blog.backend.resources;

import br.com.fiap.javaadv.blog.backend.infrastructure.config.JwtHelper;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AuthRequest;
import br.com.fiap.javaadv.blog.backend.resources.dtos.AuthResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.TokenRefreshRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;



    //temp token eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3NzkxMTQ2ODYsImV4cCI6MTc3OTIwMTA4Nn0.xbaM4ZReHcAKI804N9dIx54_VGIw1_lZ1mWN6ZP3GBo
    // curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username":"admin@gmail.com","passwprd":"admin123"}'
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){

        System.out.println("USER/PASS: "+request.username() + " " + request.password());
        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails user = (UserDetails) auth.getPrincipal();

        String accessToken = this.jwtHelper.generateToken(user);
        String refreshToken = this.jwtHelper.generateRefreshToken(user);

        return ResponseEntity.ok( new AuthResponse(accessToken, refreshToken));
    }

    //curl -X GET "http://localhost:8080/api/v1/profiles" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3NzkxMTQ2ODYsImV4cCI6MTc3OTIwMTA4Nn0.xbaM4ZReHcAKI804N9dIx54_VGIw1_lZ1mWN6ZP3GBo"


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh( @RequestBody TokenRefreshRequest request){

        String username = this.jwtHelper.extractUsername(request.refreshToken());
        UserDetails user = (UserDetails) this.userDetailsService.loadUserByUsername(username);

        if( jwtHelper.isTokeValid(request.refreshToken(), user)){
            String newAccessToken = this.jwtHelper.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(newAccessToken, request.refreshToken()));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh Token inválido ou expirado ");
        }
    }
}
