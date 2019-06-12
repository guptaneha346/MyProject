package com.myproject.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.model.User;
import com.myproject.model.UserSignup;
import com.myproject.repository.UserRepository;

import com.myproject.repository.UserLoginRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sun.rowset.internal.Row;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.myproject.repository.RoleRepository;

import java.awt.print.Pageable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.transaction.Transactional;

import com.myproject.model.Role;
import org.springframework.web.multipart.MultipartFile;

@Service("userService")

@Transactional
public class UserServiceImpl implements UserService {



    @Autowired
    UserRepository userRepository;

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
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
            for (String[] row : rows) {
                userRepository.save(new User(row[0], row[1],row[2],Integer.parseInt(row[3]),FilenameUtils.getExtension(file.getOriginalFilename())));

            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }








}