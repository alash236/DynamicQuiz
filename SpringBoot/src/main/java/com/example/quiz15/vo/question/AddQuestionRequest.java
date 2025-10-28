package com.example.quiz15.vo.question;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;


public class AddQuestionRequest {

    @NotBlank(message = "問卷名稱不能為空或全空白字串")
    private String name;

    @NotBlank(message = "問卷敘述不能為空或全空白字串")
    private String text;

    @NotNull(message = "開始時間不能為空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_Time;

    @NotNull(message = "結束時間不能為空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_Time;

    private boolean publish;

    @Valid
    @NotEmpty(message = "問卷不能為空")
    private List<QuizVo> quizList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getStart_Time() {
        return start_Time;
    }

    public void setStart_Time(LocalDate start_Time) {
        this.start_Time = start_Time;
    }

    public LocalDate getEnd_Time() {
        return end_Time;
    }

    public void setEnd_Time(LocalDate end_Time) {
        this.end_Time = end_Time;
    }

    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public List<QuizVo> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<QuizVo> quizList) {
        this.quizList = quizList;
    }
}
