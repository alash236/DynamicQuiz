package com.example.quiz15.entity.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "user")
public class User {

    //不能為空或全空白
    @NotBlank(message = "信箱不能為空或是全空白字串")
    //EMAIL格式檢查
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "信箱格式錯誤")
    @Id
    @Column(name = "email")
    private String email;

    //不能為空或全空白
    @NotBlank(message ="使用者名稱不能為空或是全空白字串")
    @Column(name = "name")
    private String name;

    //不能為空或全空白
    @NotBlank(message = "密碼不能為空或是全空白字串")
    @Column(name = "password")
    private String password;

    //不能為空或全空白
    @NotBlank(message = "電話號碼不能為空或是全空白字串")
    //手機格式檢查
    @Pattern(regexp = "09\\d{8}",message = "電話號碼格式錯誤")
    @Column(name = "phone")
    private String phone;

    //年齡須為正數
    @Min(value = 0, message = "年齡必須大於0")
    @Column(name = "age")
    private int age;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
