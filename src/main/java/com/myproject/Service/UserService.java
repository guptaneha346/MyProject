package com.myproject.Service;

import com.myproject.model.User;

import com.myproject.model.UserSignup;
import java.util.List;

public interface UserService {
    public List<User> getAllDetails();

    public User getUserById(long id);

    public void saveOrUpdate(User user);

    public void deleteUser(long id);

    public UserSignup findUserByEmail(String email);

    public void saveUser(UserSignup user);


}




