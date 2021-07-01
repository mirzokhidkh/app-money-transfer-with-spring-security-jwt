package uz.mk.appmoneytransferwithspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.mk.appmoneytransferwithspringsecurity.payload.ApiResponse;
import uz.mk.appmoneytransferwithspringsecurity.payload.LoginDto;
import uz.mk.appmoneytransferwithspringsecurity.payload.RegisterDto;
import uz.mk.appmoneytransferwithspringsecurity.security.JwtProvider;
import uz.mk.appmoneytransferwithspringsecurity.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


//    @PostMapping("/register")
//    public HttpEntity<?> register(@RequestBody RegisterDto registerDto) {
//        ApiResponse response = authService.register(registerDto);
//        return ResponseEntity.status(response.isSuccess() ? 201: 409).body(response);
//    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse response = authService.login(loginDto);
        return ResponseEntity.status(response.isSuccess() ? 200: 401).body(response);
    }



//    @Autowired
//    JwtProvider jwtProvider;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    @Autowired
//    AuthenticationManager authenticationManager;

//    @PostMapping("/login")
//    public HttpEntity<?> loginToSystem(@RequestBody LoginDto loginDto) {
//        try {
////        UserDetails userDetails = authService.loadUserByUsername(loginDto.getUsername());
////        boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
////        if (matches) {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    loginDto.getUsername(),
//                    loginDto.getPassword()));
//
//            String token = jwtProvider.generateToken(loginDto.getUsername());
//            return ResponseEntity.ok(token);
////        }
//        } catch (BadCredentialsException exception) {
//            return ResponseEntity.status(401).body("Login or password is wrong !");
//        }
//    }


}
