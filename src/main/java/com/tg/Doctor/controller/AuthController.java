//package com.tg.Doctor.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tg.Doctor.models.User;
//import com.tg.Doctor.service.AuthService;
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//	@Autowired
//    private AuthService authService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        User newUser = authService.register(user);
//        return ResponseEntity.ok(newUser);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        String token = authService.login(user.getUserName(), user.getPassword());
//        if (token != null) {
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }
//}
