package br.com.digitalhouse.clinica.Clinica.api.controller;

import br.com.digitalhouse.clinica.Clinica.domain.repository.UserRepository;
import br.com.digitalhouse.clinica.Clinica.domain.user.AuthenticationDTO;
import br.com.digitalhouse.clinica.Clinica.domain.user.LoginResponseDTO;
import br.com.digitalhouse.clinica.Clinica.domain.user.RegisterDTO;
import br.com.digitalhouse.clinica.Clinica.domain.user.User;
import br.com.digitalhouse.clinica.Clinica.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
@Autowired
private AuthenticationManager authenticationManager;

@Autowired
 private TokenService tokenService;

@Autowired
private UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
var auth = this.authenticationManager.authenticate(usernamePassword);

var token = tokenService.generateToken((User) auth.getPrincipal());

return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
if (this.userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
User newUser = new User(data.login(), encryptedPassword, data.role());

this.userRepository.save(newUser);

return ResponseEntity.ok().build();
    }
}
