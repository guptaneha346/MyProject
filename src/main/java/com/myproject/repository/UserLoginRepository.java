package com.myproject.repository;

import com.myproject.model.User;
import com.myproject.model.UserSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userLoginRepository")
public interface UserLoginRepository extends JpaRepository<UserSignup, Long> {

    UserSignup findByEmail(String email);


}
