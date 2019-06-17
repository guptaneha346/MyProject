package com.myproject.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="department")
    private String department;

    @Column(name="number")
    private int number;

    @Column(name="extensionnumber")
    private int extensionnumber;


    @Column(name="email")
    private String email;

    @Column(name="filetype")
    private String filetype;

    @Transient
    private MultipartFile file;

    public  User(){}
    public User(String name,String department,String email,int number,int extensionnumber) {
        this.name = name;
        this.department=department;
        this.number=number;
        this.extensionnumber=extensionnumber;
        this.email=email;
    }


    public User(String name,String department,String email,int number,int extensionnumber,String filetype) {
        this.name = name;
        this.department=department;
        this.number=number;
        this.extensionnumber=extensionnumber;
        this.email=email;
        this.filetype=filetype;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExtensionnumber() {
        return extensionnumber;
    }

    public void setExtensionnumber(int extensionnumber) {
        this.extensionnumber = extensionnumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", number=" + number +
                ", extensionnumber=" + extensionnumber +
                ", email='" + email + '\'' +
                ", filetype='" + filetype + '\'' +
                ", file=" + file +
                '}';
    }
}
