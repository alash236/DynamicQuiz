package com.example.quiz15.vo.question;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;


public class QuizVo {

    @Min(value = 1,message = "問卷題目ID不能小於1")
    private int quiz_id;

    @NotBlank(message = "問卷題目不能為空或全空白字串")
    private String name;

    @NotBlank(message = "問卷型態不能為空或全空白字串")
    private String type;

    private List<String> option;

    private boolean is_required;

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public boolean isIs_required() {
        return is_required;
    }

    public void setIs_required(boolean is_required) {
        this.is_required = is_required;
    }
}
