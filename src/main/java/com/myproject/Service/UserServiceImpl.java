package com.myproject.Service;

import com.myproject.model.User;
import com.myproject.model.UserSignup;
import com.myproject.repository.UserRepository;

import com.myproject.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.myproject.repository.RoleRepository;
import java.util.Arrays;
import java.util.HashSet;
import javax.transaction.Transactional;
import java.util.List;
import com.myproject.model.Role;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
  private  UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserLoginRepository userLoginRepository;




    @Override
    public List<User> getAllDetails() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }



    @Override
    public UserSignup findUserByEmail(String email) {

        return userLoginRepository.findByEmail(email);

    }

    @Override
    public void saveUser(UserSignup user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userLoginRepository.save(user);
    }


}
