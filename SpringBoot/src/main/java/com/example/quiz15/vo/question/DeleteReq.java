package com.example.quiz15.vo.question;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class DeleteReq {

    @NotEmpty(message = "List不能為空")
    List<Integer> IdList;

    public List<Integer> getIdList() {
        return IdList;
    }

    public void setIdList(List<Integer> IdList) {
        this.IdList = IdList;
    }
}
