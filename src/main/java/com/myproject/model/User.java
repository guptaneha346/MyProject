package com.myproject.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;


@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Name")
    private String name;

    @Column(name="Department")
    private String department;

    @Column(name="Phonenumber")
    private int number;

    @Column(name="Extensionnumber")
    private int extensionnumber;


    @Column(name="Email")
    private String email;

    @Column(name="Floor")
    private String floor;

    @Column(name="filetype")
    private String filetype;

    @Transient
    private MultipartFile file;

    public  User(){}
    public User(String name,String department,String floor,int extensionnumber) {
        this.name = name;
        this.department=department;
        this.floor=floor;
        this.extensionnumber=extensionnumber;

    }


    public User(String name,String department,String floor,int extensionnumber,String filetype) {
        this.name = name;
        this.department=department;
        this.floor=floor;
        this.extensionnumber=extensionnumber;

        this.filetype=filetype;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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
                ", floor='" + floor + '\'' +
                ", filetype='" + filetype + '\'' +
                ", file=" + file +
                '}';
    }
}

