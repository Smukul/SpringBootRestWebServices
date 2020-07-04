package com.webservices.Rest.repository;

import com.webservices.Rest.entity.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJPA,Integer> {
}
