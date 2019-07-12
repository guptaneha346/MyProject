package com.myproject.Service;

import com.myproject.model.EmployeeSignup;
import com.myproject.model.User;

import com.myproject.model.UserSignup;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public List<User> getAllDetails();

    public User getUserById(long id);



    public String saveOrUpdate(User user);

    public void deleteUser(long id);

    public UserSignup findUserByEmail(String email);

    public EmployeeSignup findEmployeeByEmail(String email);


    public void saveUser(UserSignup user);

    public void saveEmployee(EmployeeSignup user);

    public boolean isUserAlreadyPresent(UserSignup user);

    boolean savDatafromuploadfile(MultipartFile file);

    public List<User> findByName(String name);

    public List<User>  findByDepartment(String department);

    public List<User>  findByNameAndDepartment(String name,String department);

    public boolean CsvRowError();

    public boolean CsvNumberError();
}




