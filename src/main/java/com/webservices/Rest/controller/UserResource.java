package com.webservices.Rest.controller;

import com.webservices.Rest.entity.User;
import com.webservices.Rest.exception.UserNotFoundException;
import com.webservices.Rest.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService userDaoService;

    //retrieve all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User find = userDaoService.findOne(id);
        if(find==null){
            throw new UserNotFoundException("user id -> "+id);
        }
        //HATEOAS - return other links
        EntityModel<User> entityMode = new EntityModel<User>(find);

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.
                methodOn(this.getClass()).getAllUsers());
        entityMode.add(linkTo.withRel("all-users"));

        //return userDaoService.findOne(id);
        return entityMode;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);
        //Update return status code as created
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable int id){
        User find = userDaoService.deleteById(id);
        if(find==null){
            throw new UserNotFoundException("user id -> "+id);
        }
        return find;
    }
}
