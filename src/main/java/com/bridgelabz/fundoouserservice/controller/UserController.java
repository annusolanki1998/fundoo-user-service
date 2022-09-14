package com.bridgelabz.fundoouserservice.controller;

import com.bridgelabz.fundoouserservice.dto.UserDTO;
import com.bridgelabz.fundoouserservice.model.UserModel;
import com.bridgelabz.fundoouserservice.service.IUserService;
import com.bridgelabz.fundoouserservice.util.Response;
import com.bridgelabz.fundoouserservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to fundoo user project";
    }

    @PostMapping("/addUser")
    public ResponseEntity<Response> addUser(@Valid @RequestBody UserDTO userDTO) {
        Response response = userService.addUser(userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUtil> login(@RequestParam String emailId,
                                              @RequestParam String password) {
        ResponseUtil response = userService.login(emailId, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDTO userDTO,
                                               @PathVariable Long id,
                                               @RequestHeader String token) {
        Response response = userService.updateUser(userDTO, id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<List> getUsers(@RequestHeader String token) {
        List<UserModel> responseUtil = userService.getUsers(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Response> deleteUser(@RequestHeader String token,
                                               @RequestParam Long id) {
        Response response = userService.deleteUser(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Response> getUser(@RequestParam Long id,
                                            @RequestHeader String token) {
        Response response = userService.getUser(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Response> updatePassword(@RequestHeader String token,
                                                   @RequestParam String password) {
        Response response = userService.updatePassword(token, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<Response> resetPassword(@RequestParam String emailId) {
        Response response = userService.resetPassword(emailId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/validate/{token}")
    public Boolean validate(@PathVariable String token) {
        return userService.validate(token);
    }

    @PutMapping("/restoreUser")
    public ResponseEntity<Response> restoreUser(@RequestParam Long id,
                                                @RequestHeader String token) {
        Response response = userService.restoreUser(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<Response> deleteUsers(@RequestParam Long id,
                                                @RequestHeader String token) {
        Response response = userService.deleteUsers(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/deletePermanent")
    public ResponseEntity<Response> deletePermanent(@RequestParam Long id,
                                                    @RequestHeader String token) {
        Response response = userService.deletePermanent(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addProfilePic/{id}")
    public ResponseEntity<Response> addProfilePic(@RequestParam Long id,
                                                  @RequestBody MultipartFile profilePic) throws IOException {
        Response response = userService.addProfilePic(id, profilePic);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/validateEmail")
    public Boolean validateEmail(@RequestParam String emailId) {
        return userService.validateEmail(emailId);
    }
}




