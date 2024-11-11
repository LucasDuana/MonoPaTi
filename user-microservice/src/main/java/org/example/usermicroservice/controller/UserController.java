package org.example.usermicroservice.controller;

import jakarta.validation.Valid;
import org.example.usermicroservice.dtos.AccountDTO;
import org.example.usermicroservice.dtos.UserDTO;
import org.example.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Basic CRUD operations for User entity


    //GET /users
    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(this.userService.getUsers());
    }

    //GET /users/{id}
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


    //POST /users
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO createdUser = userService.addUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    //PUT /users/{id}
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}/discount")
    public ResponseEntity<AccountDTO> chargeUser(@PathVariable Long userId, @RequestParam Double amount) {
        AccountDTO accountDTO = userService.chargeUser(userId, amount*(-1));
        return ResponseEntity.ok(accountDTO);
    }

    //POST /users/{userId}/accounts/{accountId}
    @PostMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<UserDTO> addAccountToUser(@PathVariable Long userId, @PathVariable Long accountId) {
        UserDTO userDTO = userService.addAccountToUser(userId, accountId);
        return ResponseEntity.ok(userDTO);
    }

}
