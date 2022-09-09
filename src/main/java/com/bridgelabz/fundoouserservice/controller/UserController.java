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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/welcome")
    public String welcomeMessage(){
        return "Welcome to fundoo user project";
    }

    @PostMapping("/addUser")
    public ResponseEntity<ResponseUtil> addUser(@Valid @RequestBody UserDTO userDTO){
        ResponseUtil responseUtil = userService.addUser(userDTO);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestParam String emailId,
                                          @RequestParam String password){
        Response response= userService.login(emailId,password);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseUtil> updateUser(@Valid @RequestBody UserDTO userDTO,
                                                   @PathVariable Long id,
                                                   @RequestHeader String token){
        ResponseUtil responseUtil = userService.updateUser(userDTO,id,token);
        return new ResponseEntity<>(responseUtil,HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<List> getUsers(@RequestHeader String token){
        List<UserModel> responseUtil = userService.getUsers(token);
        return new ResponseEntity<>(responseUtil,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ResponseUtil> deleteUser(@RequestHeader String token,
                                                   @RequestParam Long id){
        ResponseUtil responseUtil = userService.deleteUser(id,token);
        return new ResponseEntity<>(responseUtil,HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<ResponseUtil> getUser(@RequestParam Long id,
                                                @RequestHeader String token){
        ResponseUtil responseUtil = userService.getUser(id,token);
        return new ResponseEntity<>(responseUtil,HttpStatus.OK);
    }

}
