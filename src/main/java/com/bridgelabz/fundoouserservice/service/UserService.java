package com.bridgelabz.fundoouserservice.service;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.exception.FundooUserNotFoundException;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.repository.UserRepository;
import com.bridgelabz.fundoouserservice.util.Response;
import com.bridgelabz.fundoouserservice.util.ResponseUtil;
import com.bridgelabz.fundoouserservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;


    @Override
    public ResponseUtil addUser(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        userModel.setCreatedAt(LocalDateTime.now());
        userRepository.save(userModel);
        String body = "Fundoo user is added sucessfully with userId " + userModel.getId();
        String subject = "Fundoo user is added sucessfully";
        mailService.send(userModel.getEmailId(), body, subject);
        return new ResponseUtil(200, "sucessfully", userModel);
    }

    @Override
    public Response login(String emailId, String password) {
        Optional<UserModel> isEmailPresent = userRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new Response(200, "Login sucessfully", token);
            }
            throw new FundooUserNotFoundException(400, "Password is wrong");
        }
        throw new FundooUserNotFoundException(400, "No user is present with this email id");
    }

    @Override
    public ResponseUtil updateUser(UserDTO userDTO, Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isId = userRepository.findById(userId);
        if (isId.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                isUserPresent.get().setName(userDTO.getName());
                isUserPresent.get().setEmailId(userDTO.getEmailId());
                isUserPresent.get().setPassword(userDTO.getPassword());
                isUserPresent.get().setIsActive(userDTO.getIsActive());
                isUserPresent.get().setIsDeleted(userDTO.getIsDeleted());
                isUserPresent.get().setDOB(userDTO.getDOB());
                isUserPresent.get().setPhoneNumber(userDTO.getPhoneNumber());
                isUserPresent.get().setProfilePic(userDTO.getProfilePic());
                userRepository.save(isUserPresent.get());
                String body = "Fundoo user is added sucessfully with userId" + isUserPresent.get().getId();
                String subject = "Fundoo user is added sucessfully";
                mailService.send(isUserPresent.get().getEmailId(), body, subject);
                return new ResponseUtil(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<UserModel> getUsers(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isId = userRepository.findById(userId);
        if (isId.isPresent()) {
            List<UserModel> isUserPresent = userRepository.findAll();
            if (isUserPresent.size() > 0) {
                return isUserPresent;
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseUtil deleteUser(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isId = userRepository.findById(userId);
        if (isId.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                userRepository.delete(isUserPresent.get());
                return new ResponseUtil(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }

    @Override
    public ResponseUtil getUser(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isId = userRepository.findById(userId);
        if (isId.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                return new ResponseUtil(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }
}
