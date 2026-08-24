package com.ashis.repositories;

import com.ashis.entities.User;
import com.ashis.utils.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsByRole(Roles roles);

}
