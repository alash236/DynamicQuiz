package com.example.quiz15.service.user.impl;

import com.example.quiz15.dao.user.UserDao;
import com.example.quiz15.entity.user.User;
import com.example.quiz15.service.user.ifs.UserService;
import com.example.quiz15.vo.user.AddUserRequest;
import com.example.quiz15.vo.user.LoginUserRequest;
import com.example.quiz15.vo.user.UpdateUserRequest;
import com.example.quiz15.vo.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    UserDao userDao;

    @Override
    public UserResponse addUser(AddUserRequest addUserRequest) {

        //檢查User資訊是否存在
        if(userDao.getUser(addUserRequest.getEmail())==1){
            return new UserResponse(400,"信箱已存在");
        }

        try {
            userDao.addUser(
                    addUserRequest.getName(),
                    encoder.encode(addUserRequest.getPassword()),
                    addUserRequest.getEmail(),
                    addUserRequest.getPhone(),
                    addUserRequest.getAge());
            return new UserResponse(200, "新增信箱成功");
        } catch (Exception e) {
            return new UserResponse(400,e.getMessage());
        }
    }


    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User user = userDao.searchUserByEmail(updateUserRequest.getEmail());
        if(user==null){
            return new UserResponse(404, "使用者資料為空");
        }

        try {
            if(!encoder.matches(updateUserRequest.getPassword(), user.getPassword())){
                return new UserResponse(400,"使用者密碼不匹配");
            }
            userDao.updateUser(
                    updateUserRequest.getName(),
                    encoder.encode(updateUserRequest.getNew_password()),
                    updateUserRequest.getEmail(),
                    updateUserRequest.getPhone(),
                    updateUserRequest.getAge());
            return new UserResponse(200, "使用者資訊更新成功");
        } catch (Exception e) {
            return new UserResponse(400,e.getMessage());
        }
    }

    @Override
    public UserResponse login(LoginUserRequest loginUserRequest) {

        User user = userDao.searchUserByEmail(loginUserRequest.getEmail());
        if(user==null){
            return new UserResponse(404, "登入失敗使用者資訊不存在");
        }

        try {
            if(!encoder.matches(loginUserRequest.getPassword(), user.getPassword())){
                return new UserResponse(400, "密碼錯誤請重新輸入");
            }
            return new UserResponse(200, "登入成功"+
                    " 你好 " + user.getName(),user);
        } catch (Exception e) {
            return new UserResponse(400,e.getMessage());
        }
    }


    @Override
    public UserResponse searchAllUser() {
        List<User> user = userDao.searchAllUser();
        for(User item:user){
            if(item == null){
                return new UserResponse(404, "使用者資訊不存在");
            }
        }
        return new UserResponse(200, "使用者資訊查詢成功",user);
    }

    @Override
    public UserResponse searchUser(String email) {
        User user = userDao.searchUserByEmail(email);
        if(user==null){
            return new UserResponse(404,"使用者資訊不存在");
        }
        return new UserResponse(200,"使用者資訊查詢成功",user);
    }
}
