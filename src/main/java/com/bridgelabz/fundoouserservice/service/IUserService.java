package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.util.Response;
import com.bridgelabz.fundoouserservice.util.ResponseUtil;

import java.util.List;

public interface IUserService {

    ResponseUtil addUser(UserDTO userDTO);

    Response login(String emailId, String password);

    ResponseUtil updateUser(UserDTO userDTO, Long id, String token);

    List<UserModel> getUsers(String token);

    ResponseUtil deleteUser(Long id, String token);

    ResponseUtil getUser(Long id, String token);
}
