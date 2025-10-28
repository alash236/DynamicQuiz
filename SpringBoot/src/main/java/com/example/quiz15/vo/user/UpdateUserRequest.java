package com.example.quiz15.vo.user;

import com.example.quiz15.entity.user.User;
import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequest extends User {

    @NotBlank(message = "更新後密碼不能為全空或全空白字串")
    private String new_password;

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
