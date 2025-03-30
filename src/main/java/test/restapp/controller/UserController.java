package test.restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.restapp.entity.User;
import test.restapp.exception.InvalidValueException;
import test.restapp.exception.NotFoundException;
import test.restapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found! ID="+id);
        }
        return optUser.get();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        if (user.getEmail() == null) {
            throw new InvalidValueException("Required value", "email");
        }
        if (user.getBalance() == null) {
            user.setBalance(0L);
        }
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User updUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFullName(updUser.getFullName());
                    user.setEmail(updUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new NotFoundException("User not found!"));
    }

}
