package com.myproject.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.model.User;
import com.myproject.model.UserSignup;
import com.myproject.repository.UserRepository;

import com.myproject.repository.UserLoginRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.myproject.repository.RoleRepository;

import java.io.*;
import java.util.*;
import javax.transaction.Transactional;

import com.myproject.model.Role;
import org.springframework.web.multipart.MultipartFile;

@Service("userService")

@Transactional
public class UserServiceImpl implements UserService {



    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Qualifier("userLoginRepository")
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
public boolean CsvRowError(){
        return false;
}

    @Override
    public void saveUser(UserSignup userSignup) {
        userSignup.setPassword(bCryptPasswordEncoder.encode(userSignup.getPassword()));
        userSignup.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        userSignup.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userLoginRepository.save(userSignup);
    }

    @Override
    public boolean savDatafromuploadfile(MultipartFile file) {
        boolean isFlag = false;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equalsIgnoreCase("json")) {
            isFlag = readDatafromjson(file);
        } else if (extension.equalsIgnoreCase("csv")) {

            isFlag = readDatafromCsv(file);
        }

        return isFlag;
    }


    @Override
    public List<User> findByName(String name) {

        return userRepository.findByNameLike("%"+name+"%");
    }

    @Override
    public List<User> findByDepartment(String department) {

        return userRepository.findByDepartmentLike("%"+department+"%");
    }
    @Override
    public List<User> findByNameAndDepartment(String name,String department) {
        return userRepository.findByNameAndDepartmentLike("%"+name+"%","%"+department+"%");
    }




    private boolean readDatafromjson(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = Arrays.asList(mapper.readValue(inputStream, User[].class));
            if (users != null && users.size() > 0) {
                for (User user : users) {
                    user.setFiletype(FilenameUtils.getExtension(file.getOriginalFilename()));
                    userRepository.save(user);
                }
            }
            return true;
        } catch (Exception e) {
        }


        return false;
    }

    private boolean readDatafromCsv(MultipartFile file) {
        try {
            String EMAIL = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
    String header="name,department,email,number,extensionnumber";
    if(rows.isEmpty()) {
      return false; }
    for (String[] row : rows) {
        if(row[0].isEmpty()||row[1].isEmpty()||row[2].isEmpty()||row[3].isEmpty()||row[4].isEmpty()){
            System.out.println("row check");
            return userService.CsvRowError();
        }
        else  if(!(row[2]).matches(EMAIL)){

            System.out.println("email error");
            return false;
        }
       else if(row[4].length()<2||row[4].length()>2)
        {
            System.out.println("extensionnumber error");
            return false;

        }
       else if(row[3].length()<10||row[3].length()>10)
        {
            System.out.println("number error");
            return false;

        }

       userRepository.save(new User(row[0], row[1], row[2], Integer.parseInt(row[3]), Integer.parseInt(row[4]), FilenameUtils.getExtension(file.getOriginalFilename())));
    }

    return true;
        } catch (Exception e)
        {
System.out.println("error");
            return false;
        }
    }

}