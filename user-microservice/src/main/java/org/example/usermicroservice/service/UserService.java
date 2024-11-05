package org.example.usermicroservice.service;


import org.example.usermicroservice.dtos.UserDTO;
import org.example.usermicroservice.repositories.AccountRepository;
import org.example.usermicroservice.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.usermicroservice.model.User;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<UserDTO> getUsers(){
        return this.userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id){
        return this.modelMapper.map(this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found")), UserDTO.class);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO){
        User user = this.modelMapper.map(userDTO, User.class);
        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO addUser(UserDTO userDTO){

        User user = this.modelMapper.map(userDTO, User.class);

        User savedUser = this.userRepository.save(user);

        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO addAccountToUser(Long userId, Long accountId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.getAccounts().add(this.accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found")));
        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDTO.class);
    }
}
