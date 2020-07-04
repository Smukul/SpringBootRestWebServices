package com.webservices.Rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "All details about user")
@Entity
public class UserJPA {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 3,message = "Name should have atleast 3 characters")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth Date should not be present")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public UserJPA() {    }

    public UserJPA(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return birthDate;
    }

    public void setDob(Date dob) {
        this.birthDate = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
