package uz.mk.appmoneytransferwithspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import uz.mk.appmoneytransferwithspringsecurity.entity.User;
import uz.mk.appmoneytransferwithspringsecurity.payload.ApiResponse;
import uz.mk.appmoneytransferwithspringsecurity.payload.LoginDto;
//import uz.mk.appmoneytransferwithspringsecurity.payload.RegisterDto;
//import uz.mk.appmoneytransferwithspringsecurity.repository.UserRepository;
import uz.mk.appmoneytransferwithspringsecurity.security.JwtProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    //
//    @Autowired
//    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;

//    public ApiResponse register(RegisterDto registerDto) {
//        boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
//        if (existsByUsername) {
//            return new ApiResponse("Such a username already exists", false);
//        }
//
//        User user = new User();
//        user.setFirstname(registerDto.getFirstname());
//        user.setLastname(registerDto.getLastname());
//        user.setUsername(registerDto.getUsername());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        userRepository.save(user);
//        return new ApiResponse("Registered successfully", true);
//    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginDto.getUsername(),
                                    loginDto.getPassword()));

            String token = jwtProvider.generateToken(loginDto.getUsername());
            return new ApiResponse("Token", true, token);

        } catch (BadCredentialsException exception) {
            return new ApiResponse("Login or password incorrect", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("john", passwordEncoder.encode("123"),new ArrayList<>()),
                new User("jane", passwordEncoder.encode("123"),new ArrayList<>()),
                new User("sara", passwordEncoder.encode("123"),new ArrayList<>())
        )
        );
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

//        Optional<User> optionalUser = userRepository.findByUsername(username);

//        if (optionalUser.isPresent()) {
//            return optionalUser.get();
//        }

        throw new UsernameNotFoundException("User not found");
    }
}
