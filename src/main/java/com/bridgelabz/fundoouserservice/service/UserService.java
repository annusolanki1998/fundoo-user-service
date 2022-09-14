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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Response addUser(UserDTO userDTO) {
        UserModel userModel = new UserModel(userDTO);
        userModel.setCreatedAt(LocalDateTime.now());
        userRepository.save(userModel);
        String body = "Fundoo user is added sucessfully with userId " + userModel.getId();
        String subject = "Fundoo user is added sucessfully";
        mailService.send(userModel.getEmailId(), body, subject);
        return new Response(200, "sucessfully", userModel);
    }

    @Override
    public ResponseUtil login(String emailId, String password) {
        Optional<UserModel> isEmailPresent = userRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new ResponseUtil(200, "Login sucessfully", token);
            }
            throw new FundooUserNotFoundException(400, "Password is wrong");
        }
        throw new FundooUserNotFoundException(400, "No user is present with this email id");
    }

    @Override
    public Response updateUser(UserDTO userDTO, Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                isUserPresent.get().setName(userDTO.getName());
                isUserPresent.get().setEmailId(userDTO.getEmailId());
                isUserPresent.get().setPassword(userDTO.getPassword());
                isUserPresent.get().setDOB(userDTO.getDOB());
                isUserPresent.get().setPhoneNumber(userDTO.getPhoneNumber());
                userRepository.save(isUserPresent.get());
                String body = "Fundoo user is added sucessfully with userId" + isUserPresent.get().getId();
                String subject = "Fundoo user is added sucessfully";
                mailService.send(isUserPresent.get().getEmailId(), body, subject);
                return new Response(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<UserModel> getUsers(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
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
    public Response deleteUser(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                userRepository.delete(isUserPresent.get());
                return new Response(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response getUser(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                return new Response(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not present");
            }
        }
        throw new FundooUserNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response updatePassword(String token, String password) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            isIdPresent.get().setPassword(password);
            userRepository.save(isIdPresent.get());
            return new Response(200, "Sucessfully", isIdPresent.get());
        } else {
            throw new FundooUserNotFoundException(400, "Token is wrong");
        }
    }

    @Override
    public Response resetPassword(String emailId) {
        Optional<UserModel> isEmailPresent = userRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            String token = tokenUtil.createToken(isEmailPresent.get().getId());
            String url = System.getenv("url");
            String body = "Reset the password using this link \n " + url +
                    "\n This token is use to reset the password \n" + token;
            String subject = "Reset password sucessfully";
            mailService.send(isEmailPresent.get().getEmailId(), body, subject);
            return new Response(200, "Sucessfull", isEmailPresent.get());
        } else {
            throw new FundooUserNotFoundException(400, "Wrong email id");
        }

    }

    @Override
    public Boolean validate(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Response restoreUser(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                isUserPresent.get().setActive(true);
                isUserPresent.get().setDeleted(false);
                userRepository.save(isUserPresent.get());
                return new Response(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not found with this id");
            }
        } else {
            throw new FundooUserNotFoundException(400, "Token is wrong");
        }
    }

    @Override
    public Response deleteUsers(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isIdPresent = userRepository.findById(userId);
        if (isIdPresent.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                isUserPresent.get().setActive(false);
                isUserPresent.get().setDeleted(true);
                userRepository.save(isUserPresent.get());
                return new Response(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not found with this id");
            }
        } else {
            throw new FundooUserNotFoundException(400, "Token is wrong");
        }
    }

    @Override
    public Response deletePermanent(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<UserModel> isId = userRepository.findById(userId);
        if (isId.isPresent()) {
            Optional<UserModel> isUserPresent = userRepository.findById(id);
            if (isUserPresent.isPresent()) {
                userRepository.delete(isUserPresent.get());
                return new Response(200, "Sucessfully", isUserPresent.get());
            } else {
                throw new FundooUserNotFoundException(400, "User not found with this id");
            }
        } else {
            throw new FundooUserNotFoundException(400, "Token is wrong");
        }

    }

    @Override
    public Response addProfilePic(Long id, MultipartFile profilePic) throws IOException {
        Optional<UserModel> isIdPresent = userRepository.findById(id);
        if (isIdPresent.isPresent()) {
            isIdPresent.get().setProfilePic(String.valueOf(profilePic.getBytes()).getBytes());
            userRepository.save(isIdPresent.get());
            return new Response(200, "Sucessfully", isIdPresent.get());
        } else {
            throw new FundooUserNotFoundException(400, "User not found with this id");
        }
    }

    @Override
    public Boolean validateEmail(String emailId) {
        Optional<UserModel> isEmailPresent = userRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


}

