package com.spring.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.board.entity.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
}
