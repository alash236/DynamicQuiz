package com.example.quiz15.entity.feedback;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@IdClass(FeedBackId.class)
@Table(name="feedback")
public class FeedBack {

    @NotNull(message = "問卷ID不能為空")
    @Id
    @Column(name = "question_id")
    private int question_id;

    @NotBlank(message = "使用者名稱不能為空或全空白字串")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "使用者信箱不能為空或全空白字串")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "信箱格式錯誤")
    @Id
    @Column(name="email")
    private String email;

    @NotNull(message = "填寫時間不能為空")
    @Column(name="writetime")
    private LocalDateTime writetime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
