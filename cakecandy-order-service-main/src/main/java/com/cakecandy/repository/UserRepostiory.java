package com.cakecandy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakecandy.entity.User;

public interface UserRepostiory extends JpaRepository<User, Long> {

}
