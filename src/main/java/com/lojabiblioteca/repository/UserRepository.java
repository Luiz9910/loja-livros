package com.lojabiblioteca.repository;

import com.lojabiblioteca.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "select * from user where email = :email")
    Optional<User> findByEmail(String email);
}
