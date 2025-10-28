package com.example.quiz15.service.answer.impl;

import com.example.quiz15.dao.answer.AnswerDao;
import com.example.quiz15.dao.quiz.QuizDao;
import com.example.quiz15.entity.answer.Answer;
import com.example.quiz15.service.answer.ifs.AnswerService;
import com.example.quiz15.vo.answer.AddAnswer;
import com.example.quiz15.vo.answer.AnswerRes;
import com.example.quiz15.vo.answer.AnswerResponse;
import com.example.quiz15.vo.answer.AnswerVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerDao answerDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    ObjectMapper mapper;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public AnswerResponse addAnswer(AddAnswer addAnswer) throws Exception {
        try {
            List<AnswerVo> answer = addAnswer.getAnswerList();
            if (answer == null || answer.isEmpty()) {
                throw new Exception("答案資訊為空");
            }

            for (AnswerVo answeritem : answer) {
                String option = quizDao.getQuizOption(answeritem.getQuestion_id(),answeritem.getQuiz_id());
                String type = quizDao.getQuizType(answeritem.getQuestion_id(),answeritem.getQuiz_id());
                Byte b = quizDao.getQuizIs_Required(answeritem.getQuestion_id(),answeritem.getQuiz_id());
                boolean is_required = b != 0;
                List<String> selected = answeritem.getAnsweroption();

                if (option == null) {
                    throw new Exception("無效的 quiz ID: " + answeritem.getQuiz_id());
                }

                if(is_required && (selected==null || selected.isEmpty())){
                    throw new Exception("請填寫第 " + answeritem.getQuiz_id() + " 題的答案");
                }

                List<String> validOptions = mapper.readValue(quizDao.getValidOptions(answeritem.getQuestion_id(), answeritem.getQuiz_id()),
                        new TypeReference<>(){});
                Set<String> validSet = new HashSet<>();

                for(String item:validOptions){
                    if(!type.equals("text")){
                        validSet.add(item);
                    }
                }
                System.out.println(validSet);

                for (String item : selected) {
                    if (is_required && (item == null || item.trim().isEmpty())) {
                        throw new Exception("第 " + answeritem.getQuiz_id() + " 題中有空白選項");
                    }
                    if(!type.equals("text")){
                        String normalizedOption = item.trim();
                        if (!validSet.contains(normalizedOption)) {
                            throw new Exception("第 " + answeritem.getQuiz_id() + " 題選項【" + normalizedOption + "】沒有此選項");
                        }
                    }
                }

                answerDao.addAnswer(
                        answeritem.getQuestion_id(),
                        answeritem.getQuiz_id(),
                        answeritem.getUsername(),
                        mapper.writeValueAsString(answeritem.getAnsweroption()));
            }

            return new AnswerResponse(200, "新增答案成功");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AnswerResponse searchAnswer(int question_id) {
        List<Answer> answerList = answerDao.getAnswer(question_id);
        List<AnswerRes> resultList = new ArrayList<>();
        if (answerList == null || answerList.isEmpty()) {
            return new AnswerResponse(404, "查無答案");
        }
        for (Answer answer : answerList) {
            try {
                AnswerRes res = new AnswerRes(
                        answer.getQuestion_id(),
                        answer.getQuiz_id(),
                        answer.getUsername(),
                        answer.getAnsweroption()

                );
                resultList.add(res);
            } catch (Exception e) {
                return new AnswerResponse(500, "資料轉換失敗: " + e.getMessage());
            }
        }
        return new AnswerResponse(200, "答案資訊查詢成功", resultList);
    }

    @Override
    public AnswerResponse searchAllAnswer() {
        List<Answer> answerList = answerDao.getAllAnswer();
        List<AnswerRes> res = new ArrayList<>();

        for (Answer answer : answerList) {
            try {
                AnswerRes answerRes = new AnswerRes(
                        answer.getQuestion_id(),
                        answer.getQuiz_id(),
                        answer.getUsername(),
                        answer.getAnsweroption()
                );
                res.add(answerRes);
            } catch (Exception e) {
                return new AnswerResponse(500, "資料轉換失敗: " + e.getMessage());
            }
        }
        return new AnswerResponse(200, "答案查詢成功", res);
    }

    @Override
    public AnswerResponse searchSingleAnswer(int question_id, String username) {
        List<Answer> answerList = answerDao.getSingleAnswer(question_id, username);
        List<AnswerRes> res = new ArrayList<>();
        for (Answer answer : answerList) {
            try {
                AnswerRes answerRes = new AnswerRes(
                        answer.getQuestion_id(),
                        answer.getQuiz_id(),
                        answer.getUsername(),
                        answer.getAnsweroption()
                );
                res.add(answerRes);
            } catch (Exception e) {
                return new AnswerResponse(500, "資料轉換失敗: " + e.getMessage());
            }
        }
        return new AnswerResponse(200, "答案資訊查詢成功", res);
    }

    @Override
    public AnswerResponse deleteAnswer(int question_id) {
        answerDao.deleteAnswer(question_id);
        return new AnswerResponse(200, "刪除成功");
    }

    @Override
    public AnswerResponse searchEmailAnswer(String username) {
        List<Answer> answerList = answerDao.getEmailAnswer(username);
        if (answerList == null) return new AnswerResponse(404, "找不到答案資訊");
        List<AnswerRes> res = new ArrayList<>();
        for (Answer answer : answerList) {
            try {
                AnswerRes answerRes = new AnswerRes(
                        answer.getQuestion_id(),
                        answer.getQuiz_id(),
                        answer.getUsername(),
                        answer.getAnsweroption()
                );
                res.add(answerRes);
            } catch (Exception e) {
                return new AnswerResponse(500, "資料轉換失敗: " + e.getMessage());
            }
        }
        return new AnswerResponse(200, "答案資訊查詢成功", res);
    }

    @Override
    public AnswerResponse deleteAnswerFromMail(int question_id, String username) {
        answerDao.deleteAnswerFromMail(question_id, username);
        return new AnswerResponse(200, "刪除成功");
    }
}
