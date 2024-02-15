package af.cmr.indyli.akademiaws.controller;

import af.cmr.indyli.akademia.business.dto.AuthRequest;
import af.cmr.indyli.akademia.business.dto.UserRegistrationDTO;
import af.cmr.indyli.akademia.business.dto.UserRegistrationResponseDTO;
import af.cmr.indyli.akademia.business.dto.basic.UserBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.UserFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.utils.ConstsValues;

import af.cmr.indyli.akademiaws.jwtService.JwtService;
import af.cmr.indyli.akademiaws.jwtService.UserInfoService;
import af.cmr.indyli.akademiaws.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserInfoService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Resource(name = ConstsValues.ServiceKeys.USER_SERVICE)
    private IUserService userService;

    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<UserFullDTO> createUser(@RequestBody UserFullDTO userDTO) throws AkdemiaBusinessException {
        UserFullDTO createdUser = userService.create(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDTO> getUserById(@PathVariable Integer id) throws AkdemiaBusinessException {
        UserFullDTO userDTO = userService.findById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserBasicDTO>> getAllUsers() {
        List<UserBasicDTO> companies = userService.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("by-email/{email}")
    public ResponseEntity<UserFullDTO> getUserByEmail(@PathVariable String email) throws AkdemiaBusinessException {
        UserFullDTO dto = this.userService.findUserByEmail(email);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDTO> registerUser(@RequestBody UserRegistrationDTO dto) throws AkdemiaBusinessException {
        UserRegistrationResponseDTO response = userService.registerUser(dto);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/generateToken")
    public ResponseEntity<Map<String, String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws AkdemiaBusinessException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserFullDTO user = userService.findUserByEmail(authRequest.getEmail());
            List<String> roles = this.service.loadUserByUsername(authRequest.getEmail()).getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            return ResponseEntity.ok(Map.of("token", jwtService.generateToken(user, roles)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "L'utilisateur n'a pas été retrouvé"));
        }
    }
}