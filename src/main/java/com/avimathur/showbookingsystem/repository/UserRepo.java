package com.avimathur.showbookingsystem.repository;

import com.avimathur.showbookingsystem.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepo {

    private static final UserRepo userRepo = new UserRepo();

    ArrayList<User> listOfUsers;

    private UserRepo(){
        listOfUsers = new ArrayList<>();
    }

    public User getUser(String name){
        for(User user : listOfUsers){
            if(user.getUserName().equals(name)){
                return user;
            }
        }
        User user = new User(name);
        listOfUsers.add(user);

        return user;
    }

}
