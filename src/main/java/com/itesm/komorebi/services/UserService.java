package com.itesm.komorebi.services;

import com.itesm.komorebi.dto.UserDTO;
import com.itesm.komorebi.models.User;
import com.itesm.komorebi.repositories.UserRepository;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    enum Roles{
        Agent,
        Supervisor,
        Manager
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()){
            return null;
        }
        UserDTO newUser = new UserDTO();
        newUser.setUsername(user.get().getEmail());
        newUser.setPassword(user.get().getPwdHash());
        return newUser;
    }

    public boolean existByEmail(String email){
        return userRepository.existsById(email);
    }
    //CREATE
    public Optional<User> insert(User user){
        if (existByEmail(user.getEmail())){
            return Optional.empty();
        }
        return Optional.of(userRepository.save(user));
    }
    //RETRIEVE
    public Optional<User> findByEmail(String email){return userRepository.findById(email);}
    public List<User> findAll(){return userRepository.findAll();}
    public List<User> findAllAgents(){return userRepository.findAllByRole(Roles.Agent.toString());}
    public List<User> findAllSupervisors(){return userRepository.findAllByRole(Roles.Supervisor.toString());}
    public List<User> findAllManagers(){return userRepository.findAllByRole(Roles.Manager.toString());}
    //UPDATE
    public Optional<User> update(User user){
        if (existByEmail(user.getEmail())){
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }
    //DELETE
}
