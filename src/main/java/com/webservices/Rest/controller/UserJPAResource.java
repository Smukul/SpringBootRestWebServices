package com.webservices.Rest.controller;

import com.webservices.Rest.entity.Post;
import com.webservices.Rest.entity.User;
import com.webservices.Rest.entity.UserJPA;
import com.webservices.Rest.exception.UserNotFoundException;
import com.webservices.Rest.repository.PostRepository;
import com.webservices.Rest.repository.UserRepository;
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
import java.util.Optional;

@RestController
public class UserJPAResource {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    //retrieve all users
    @GetMapping("/jpa/users")
    public List<UserJPA> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<UserJPA> getUser(@PathVariable int id){
        Optional<UserJPA> find = userRepository.findById(id);
        if(! find.isPresent()){
            throw new UserNotFoundException("user id -> "+id);
        }
        //HATEOAS - return other links
        EntityModel<UserJPA> entityMode = new EntityModel<UserJPA>(find.get());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.
                methodOn(this.getClass()).getAllUsers());
        entityMode.add(linkTo.withRel("all-users"));

        //return userDaoService.findOne(id);
        return entityMode;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserJPA user){
        UserJPA savedUser = userRepository.save(user);
        //Update return status code as created
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getPost(@PathVariable int id){
        Optional<UserJPA> find = userRepository.findById(id);
        if(! find.isPresent()){
            throw new UserNotFoundException("user id -> "+id);
        }

        return find.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<UserJPA> find = userRepository.findById(id);
        if(! find.isPresent()){
            throw new UserNotFoundException("user id -> "+id);
        }
        post.setUser(find.get());
        postRepository.save(post);

        //Update return status code as created
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
