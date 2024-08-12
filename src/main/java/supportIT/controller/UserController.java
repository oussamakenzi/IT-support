package supportIT.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supportIT.model.Personne;
import supportIT.repository.PersonneRepository;
import supportIT.service.implimentation.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin/gestion-users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Personne> getAllUsers() {
        return userService.getAllUsers();
    }

    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/userRole")
    public ResponseEntity<List<Personne>> getUsersByRole(@RequestParam String role) {
        List<Personne> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }




}
