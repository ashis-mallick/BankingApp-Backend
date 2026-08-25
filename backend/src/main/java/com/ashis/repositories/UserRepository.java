package com.ashis.repositories;

import com.ashis.entities.User;
import com.ashis.utils.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsByRole(Roles roles);

    Optional<User> findByUsername(String username);

}
