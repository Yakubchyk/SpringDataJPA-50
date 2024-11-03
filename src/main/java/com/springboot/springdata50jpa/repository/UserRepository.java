package com.springboot.springdata50jpa.repository;

import com.springboot.springdata50jpa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query(value = "select * from tb_users u where u.username = :username", nativeQuery = true) //SQL
    Optional<UserWithUsernameView> findByUsername(String username);

    Slice<User> findAllByName(String name, Pageable pageable);
}