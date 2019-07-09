package com.myproject.repository;

import com.myproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;


@Repository("userRepository")
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByNameLike(String name);

    List<User> findByDepartmentLike(String department);


    List<User> findByNameAndDepartmentLike(String name, String department);


}



