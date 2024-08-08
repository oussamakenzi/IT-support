package supportIT.service.implimentation;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import supportIT.auth.AuthenticationRequest;
import supportIT.auth.AuthenticationResponse;
import supportIT.auth.RegisterRequest;
import supportIT.config.JwtService;
import supportIT.enums.Role;
import supportIT.model.Personne;
import supportIT.model.Technicien;
import supportIT.model.Utilisateur;
import supportIT.repository.PersonneRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonneRepository personneRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request, Role role) {
        if (role == Role.ADMIN) {
            throw new RuntimeException("Cannot register new admin");
        }

        Personne personne;
        if (role == Role.TECHNICIEN) {
            personne = new Technicien();
        } else {
            personne = new Utilisateur();
        }

        personne.setFullname(request.getFullname());
        personne.setEmail(request.getEmail());
        personne.setPassword(passwordEncoder.encode(request.getPassword()));
        personne.setRole(role);

        personneRepository.save(personne);
        String jwtToken = jwtService.generateToken(personne);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        var user = personneRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
