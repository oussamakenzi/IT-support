package supportIT.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supportIT.auth.AuthenticationRequest;
import supportIT.auth.AuthenticationResponse;
import supportIT.service.implimentation.AuthenticationService;
import supportIT.auth.RegisterRequest;
import supportIT.enums.Role;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register-user")
    public ResponseEntity<AuthenticationResponse> register
            (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request, Role.USER));
    }

    @PostMapping("/register-technicien")
    public ResponseEntity<AuthenticationResponse> registerTechnician
            (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request, Role.TECHNICIEN));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login
            (@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
