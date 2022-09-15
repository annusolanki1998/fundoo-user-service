package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.util.Response;
import com.bridgelabz.fundoouserservice.util.ResponseUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Purpose: Creating Interface for fundoo user service
 * @author: Annu Kumari
 * @Param:   All service methods
 * Version:  1.0
 */

public interface IUserService {

    Response addUser(UserDTO userDTO);

    ResponseUtil login(String emailId, String password);

    Response updateUser(UserDTO userDTO, Long id, String token);

    List<UserModel> getUsers(String token);

    Response deleteUser(Long id, String token);

    Response getUser(Long id, String token);

    Response updatePassword(String token, String password);

    Response resetPassword(String emailId);

    Boolean validate(String token);

    Response restoreUser(Long id, String token);

    Response deleteUsers(Long id, String token);

    Response deletePermanent(Long id, String token);

    Response addProfilePic(Long id, MultipartFile profilePic) throws IOException;

    Boolean validateEmail(String emailId);
}
