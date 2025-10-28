package com.example.quiz15.service.feedback.ifs;

import com.example.quiz15.vo.feedback.FeedBackRep;
import com.example.quiz15.vo.feedback.FeedBackRes;
import com.example.quiz15.vo.feedback.UpdateFeedBackRes;

public interface FeedBackService {
    public FeedBackRep addFeedBack(FeedBackRes feedBackRes);
    public FeedBackRep searchFeedBack(int question_id);
    public FeedBackRep searchFeedBackEmail(String email);
    public FeedBackRep updateFeedBack(UpdateFeedBackRes updateFeedBackRes);
    public FeedBackRep deleteFeedBack(int question_id);
}
