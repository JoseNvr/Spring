package com.sanmina.gettingstarted.repository;

import java.util.Optional;

import com.sanmina.gettingstarted.model.UserLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users", itemResourceRel = "user")
public interface UserRepository extends JpaRepository<UserLogin, Integer> {

	Optional<UserLogin> findByUserName(String userName);

}
