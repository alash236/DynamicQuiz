package com.example.quiz15.vo.answer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class AnswerRes {
    @NotNull(message = "問卷ID不能為空")
    @Min(value = 1,message = "問卷ID不能小於1")
    private int question_id;

    @NotNull(message = "問卷題目ID不能為空")
    @Min(value = 1,message = "問卷題目ID不能小於1")
    private int quiz_id;

    @NotBlank(message = "使用者信箱不能為空或全空字串")
    private String username;

    private List<String> answeroption;

    ObjectMapper mapper = new ObjectMapper();

    public AnswerRes(int question_id,int quiz_id,String username,String answeroption) throws JsonProcessingException {
        this.question_id = question_id;
        this.quiz_id = quiz_id;
        this.username = username;
        this.answeroption = mapper.readValue(answeroption, new TypeReference<List<String>>(){});
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAnsweroption() {
        return answeroption;
    }

    public void setAnsweroption(List<String> answeroption) {
        this.answeroption = answeroption;
    }

}
