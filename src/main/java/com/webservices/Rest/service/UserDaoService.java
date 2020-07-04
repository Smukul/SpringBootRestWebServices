package com.webservices.Rest.service;

import com.webservices.Rest.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    public static int userCount = 3;
    static{
        users.add(new User(1,"Pratap", new Date()));
        users.add(new User(2,"Ram", new Date()));
        users.add(new User(3,"Vineet", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId()== id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> userIterator = users.iterator();
        while(userIterator.hasNext()) {
        User user = userIterator.next();
            if(user.getId()== id){
                userIterator.remove();
                return user;
            }
        }
        return null;
    }
}
