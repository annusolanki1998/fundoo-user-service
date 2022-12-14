package com.bridgelabz.fundoouserservice.model;


import com.bridgelabz.fundoouserservice.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/*
 * Purpose : UserModel are used to transfer the data into database
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String emailId;
    private String password;
    private boolean active;
    private boolean deleted;
    private Date DOB;
    private String phoneNumber;

    @Lob
    private byte[] profilePic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserModel(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();
        this.DOB = userDTO.getDOB();
        this.phoneNumber = userDTO.getPhoneNumber();

    }

    public UserModel() {

    }
}
