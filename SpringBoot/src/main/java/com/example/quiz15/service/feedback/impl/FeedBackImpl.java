package com.example.quiz15.service.feedback.impl;

import com.example.quiz15.dao.feedback.FeedBackDao;
import com.example.quiz15.entity.feedback.FeedBack;
import com.example.quiz15.service.feedback.ifs.FeedBackService;
import com.example.quiz15.vo.feedback.FeedBackRep;
import com.example.quiz15.vo.feedback.FeedBackRes;
import com.example.quiz15.vo.feedback.UpdateFeedBackRes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class FeedBackImpl implements FeedBackService {

    @Autowired
    FeedBackDao feedBackDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public FeedBackRep addFeedBack(FeedBackRes feedBackRes) {

        if(feedBackRes == null) return new FeedBackRep(400,"資料錯誤");
        feedBackDao.addFeedBack(feedBackRes.getQuestion_id(),
                feedBackRes.getName(),
                feedBackRes.getEmail(),
                feedBackRes.getWritetime());
        return new FeedBackRep(200,"新增成功");
    }

    @Override
    public FeedBackRep searchFeedBack(int question_id) {
        List<FeedBack> feedBack = feedBackDao.searchSingleAllFeedBack(question_id);
        if(feedBack==null)return new FeedBackRep(404,"找不到所有填寫資訊");
        for(FeedBack item:feedBack){
            if(item == null)return new FeedBackRep(404,"找不到填寫資訊");
        }
        return new FeedBackRep(feedBack,200,"查詢成功");
    }

    @Override
    public FeedBackRep searchFeedBackEmail(String email) {
        List<FeedBack> feedBack = feedBackDao.searchAllFeedBackEmail(email);
        if(feedBack==null)return new FeedBackRep(404,"找不到所有填寫資訊");
        for(FeedBack item:feedBack){
            if(item == null)return new FeedBackRep(404,"找不到填寫資訊");
        }
        return new FeedBackRep(feedBack,200,"查詢成功");
    }

    @Override
    public FeedBackRep deleteFeedBack(int question_id) {
        feedBackDao.deleteFeedBack(question_id);
        return new FeedBackRep(200,"刪除成功!!!");
    }

    @Override
    public FeedBackRep updateFeedBack(@Valid UpdateFeedBackRes update) {
        feedBackDao.updateFeedBack(update.getQuestion_id(),update.getEmail(),update.getWritetime());
        return new FeedBackRep(200,"更新成功!!!");
    }
}
