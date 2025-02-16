//package com.tg.Doctor.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.*;
//
//import com.tg.Doctor.models.JwtUtil;
//import com.tg.Doctor.models.User;
//import com.tg.Doctor.repositories.UserRespository;
//
//@Service
//public class AuthService {
//	@Autowired
//    private UserRespository userRepository;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public String login(String username, String password) {
//        Optional<User> userOptional = userRepository.findByuserName(username);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return jwtUtil.generateToken(username);
//            }
//        }
//        return null;  // Invalid credentials
//    }
//
//    public User register(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
//        return userRepository.save(user);
//    }
//
//}
