package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.util.Response;
import com.bridgelabz.fundoouserservice.util.ResponseUtil;

import java.util.List;

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
}
