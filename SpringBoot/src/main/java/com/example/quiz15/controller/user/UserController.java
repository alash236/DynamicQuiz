package com.example.quiz15.controller.user;

import com.example.quiz15.service.user.ifs.UserService;
import com.example.quiz15.vo.user.LoginUserRequest;
import com.example.quiz15.vo.user.UpdateUserRequest;
import com.example.quiz15.vo.user.UserResponse;
import com.example.quiz15.vo.user.AddUserRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/searchAllUser")
    public ResponseEntity<UserResponse> searchAllUser(){
        return ResponseEntity.ok(userService.searchAllUser());
    }

    @GetMapping(value = "/searchUser/{email}")
    public ResponseEntity<UserResponse> searchUser(@PathVariable String email){
        return ResponseEntity.ok(userService.searchUser(email));
    }

    @PostMapping(value = "/login",produces = "application/json")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginUserRequest userRequest, HttpSession httpSession){
        String storedEmail = (String) httpSession.getAttribute("email");
        if(storedEmail != null){
            return ResponseEntity.ok(new UserResponse(200,"登入成功"));
        }
        UserResponse res = userService.login(userRequest);
        if(res.getCode()==200){
            httpSession.setAttribute("email",userRequest.getEmail());
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping(value = "/add/user",produces = "application/json")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody AddUserRequest userRequest){
        UserResponse res = userService.addUser(userRequest);
        return ResponseEntity.ok(res);
    }

    @PostMapping(value = "/update/user",produces = "application/json")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest userRequest){
        UserResponse res = userService.updateUser(userRequest);
        return ResponseEntity.ok(res);
    }
}
