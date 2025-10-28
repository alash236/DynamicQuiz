package com.example.quiz15.vo.feedback;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class UpdateFeedBackRes {

    @NotNull(message = "問卷ID不能為空")
   private int question_id;

    @NotBlank(message = "信箱不能為空或是全空白字串")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "信箱格式錯誤")
   private String email;

    @NotNull(message = "更新後填寫時間不能為空")
   private LocalDateTime writetime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public LocalDateTime getWritetime() {
        return writetime;
    }

    public void setWritetime(LocalDateTime writetime) {
        this.writetime = writetime;
    }
}
