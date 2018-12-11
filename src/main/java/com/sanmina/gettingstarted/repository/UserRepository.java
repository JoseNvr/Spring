package com.sanmina.gettingstarted.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanmina.gettingstarted.model.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Integer> {

	UserLogin findByUserName(String userName);

}
