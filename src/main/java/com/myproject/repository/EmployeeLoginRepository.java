package com.myproject.repository;

import com.myproject.model.EmployeeSignup;
import com.myproject.model.User;
import com.myproject.model.UserSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeRepository")
public interface EmployeeLoginRepository extends JpaRepository<EmployeeSignup, Long> {

    EmployeeSignup findByEmail(String email);


}
