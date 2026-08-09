package todoKanban.kanban.controller;

import todoKanban.kanban.entity.User;
import todoKanban.kanban.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try{
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginData){
        try{
            Map<String, Object> user = userService.loginUser(loginData.getUsername(), loginData.getPassword());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
