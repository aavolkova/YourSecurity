package me.anna.demo.services;


import me.anna.demo.models.Userz;
import me.anna.demo.repositories.RoleRepo;
import me.anna.demo.repositories.UserzRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    UserzRepo uRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    public UserService(UserzRepo uRepo){
        this.uRepo = uRepo;
    }

    public Userz findByEmail(String email){
        return uRepo.findByEmail(email);
    }


    public Long countByEmail(String email) {
        return uRepo.countByEmail (email);

    }

    public Userz findByUsername(String username){
        return uRepo.findByUsername(username);
    }


    public void saveUserz (Userz user){
        user.setRoles(Arrays.asList(roleRepo.findByRole("USER")));
        user.setEnabled(true);
        uRepo.save(user);
    }


    public void saveAdmin (Userz user){
        user.setRoles(Arrays.asList(roleRepo.findByRole("ADMIN")));
        user.setEnabled(true);
        uRepo.save(user);
    }


}
