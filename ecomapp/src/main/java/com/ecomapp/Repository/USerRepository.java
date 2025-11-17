package com.ecomapp.Repository;

import com.ecomapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface USerRepository extends JpaRepository<User, Long> {

   Optional<User> findByEmail(String email);

}
