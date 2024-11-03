package com.springboot.springdata50jpa.controller;

import com.springboot.springdata50jpa.entity.User;
import com.springboot.springdata50jpa.repository.UserRepository;
import com.springboot.springdata50jpa.repository.UserWithUsernameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/u/{username}")
    public ResponseEntity<UserWithUsernameView> getUserByUsername(@PathVariable String username) {
        Optional<UserWithUsernameView> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return ResponseEntity.ok(byUsername.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}") //localhost:8080/user/name/Test?page=1
    public ResponseEntity<List<User>> getAllByName(@RequestParam(defaultValue = "0") int page, @PathVariable String name) {
        Slice<User> allByName = userRepository.findAllByName(name, PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id")));
        return ResponseEntity.ok(allByName.getContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updated = userRepository.save(user);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/username")
    public ResponseEntity<User> updateUserByUsername(@RequestBody UpdateUserDto dto) {
        Optional<User> byId = userRepository.findById(dto.id());
        if (byId.isPresent()) {
            User user = byId.get();
            user.setUsername(dto.value());
            User save = userRepository.save(user);
            return ResponseEntity.ok(save);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

record UpdateUserDto(Long id, String value){}
