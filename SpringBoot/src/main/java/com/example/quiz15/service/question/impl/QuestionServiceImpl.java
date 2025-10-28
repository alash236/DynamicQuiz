package com.example.quiz15.service.question.impl;

import com.example.quiz15.dao.quiz.QuizDao;
import com.example.quiz15.dao.question.QuestionDao;
import com.example.quiz15.entity.question.Question;
import com.example.quiz15.service.question.ifs.QuestionService;
import com.example.quiz15.vo.question.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class QuestionServiceImpl implements QuestionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    QuestionDao QuestionDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    private ObjectMapper mapper;


    /*  @Transactional 有效回朔的異常預設是 RunTimeException，若發生的異常不是 RunTimeException
     * 或其子類別的異常類型，資料皆不會回朔，因此想要讓只要發生任何一種異常時資料都要可以回朔，可以
     * 將 @Transactional 的有效範圍從 RunTimeException 提高至 Exception
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public QuestionResponse addQuestion(AddQuestionRequest addQuestionRequest) throws Exception {
        QuestionResponse checktime = checkTime(addQuestionRequest);
        if (checktime != null) {
            throw new Exception(checktime.getMessage());
        }
        if (addQuestionRequest.getName() == null || addQuestionRequest.getName().trim().isEmpty()) {
            throw new Exception("問題名稱不能為空");
        }

        try {
            QuestionDao.addQuestion(addQuestionRequest.getName(), addQuestionRequest.getText(), addQuestionRequest.getStart_Time(), addQuestionRequest.getEnd_Time(), addQuestionRequest.isPublish());
            int question_id = quizDao.getLatestQuizId();
            List<QuizVo> quizvo = addQuestionRequest.getQuizList();
            for (QuizVo item : quizvo) {
                List<String> optionList = item.getOption();

                Set<String> optionSet = new HashSet<>();
                for (String option : optionList) {
                    if (option == null || option.trim().isEmpty()) {
                        return new QuestionResponse(400, "選項不能為空白");
                    }
                    if (!optionSet.add(option)) {
                        return new QuestionResponse(400, "選項不能重複");
                    }
                }
                String str = mapper.writeValueAsString(optionList);
                QuestionResponse check = checkInfo(item);
                if (check != null) {
                    throw new Exception(check.getMessage());
                }
                quizDao.addQuiz(item.getQuiz_id(), question_id, item.getName(), item.getType(), item.isIs_required(), str);
            }
            return new QuestionResponse(200, "新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public QuestionResponse updateQuestion(int question_id, UpdateQuestionRequest updateQuestionRequest) throws Exception {
        try {
            QuestionDao.updateQuestion(question_id, updateQuestionRequest.getName(), updateQuestionRequest.getText(), updateQuestionRequest.getStart_Time(), updateQuestionRequest.getEnd_Time(), updateQuestionRequest.getPublish());
            quizDao.deleteQuiz(question_id);
            List<QuizVo> quizList = updateQuestionRequest.getQuizList();

            for (QuizVo item : quizList) {
                List<String> optionList = item.getOption();
                Set<String> optionSet = new HashSet<>();
                for (String option : optionList) {
                    if (option == null || option.trim().isEmpty()) {
                        return new QuestionResponse(400, "選項不能為空白");
                    }
                    if (!optionSet.add(option)) {
                        return new QuestionResponse(400, "選項不能重複");
                    }
                }
                String str = mapper.writeValueAsString(optionList);
                QuestionResponse check = checkInfo(item);
                if (check != null) {
                    throw new Exception(check.getMessage());
                }
                quizDao.addQuiz(item.getQuiz_id(), question_id, item.getName(), item.getType(), item.isIs_required(), str);
            }
            return new QuestionResponse(200, "更新成功");
        } catch (Exception e) {
            throw e;
        }
    }

    private QuestionResponse checkInfo(QuizVo item) {
        if (item.getName() == null || item.getName().trim().isEmpty()) {
            return new QuestionResponse(400, "題目名稱不能為空");
        }
        if (!item.getType().equals("single") && !item.getType().equals("text") && !item.getType().equals("multiple")) {
            return new QuestionResponse(400, "問卷型態錯誤(不是三型態之一)");
        }
        if (!item.getType().equalsIgnoreCase("text") && item.getOption().size() < 2) {
            return new QuestionResponse("不是文字題時選項必須大於等於2");
        } else if (item.getType().equalsIgnoreCase("text") && !item.getOption().isEmpty()) {
            return new QuestionResponse("是文字題時不能有選項");
        }
        return null;
    }

    private QuestionResponse checkTime(AddQuestionRequest addQuestionRequest) {
        if (addQuestionRequest.getStart_Time().isAfter(addQuestionRequest.getEnd_Time()) || addQuestionRequest.getStart_Time().isBefore(LocalDate.now())) {
            return new QuestionResponse(400, "開始時間不能比結束時間晚或是開始時間比現在時間早");
        } else if (addQuestionRequest.getEnd_Time().isBefore(LocalDate.now()) || addQuestionRequest.getEnd_Time().isBefore(addQuestionRequest.getStart_Time())) {
            return new QuestionResponse(400, "結束時間不能比現在時間早或是比開始時間早");
        }
        return null;
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public QuestionResponse deleteQuestion(int id) throws Exception {
        try {
            Question question = QuestionDao.getQuestion(id);
            if (question == null) {
                throw new Exception("找不到題目");
            }
            QuestionDao.delete(id);
            quizDao.deleteQuiz(id);
            return new QuestionResponse(200, "刪除成功");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public QuestionResponse searchAllQuestion() {
        List<Question> res = QuestionDao.getAllQuestion();
        if (res == null) {
            return new QuestionResponse(404, "找不到所有題目");
        }
        for (Question item : res) {
            if (item == null) {
                return new QuestionResponse(404, "找不到題目");
            }
        }
        return new QuestionResponse(200, res);
    }


    @Override
    public QuestionResponse searchQuestion(int id) {
        Question question = QuestionDao.getQuestion(id);
        if (question == null) {
            return new QuestionResponse(404, "找不到題目");
        }
        return new QuestionResponse(200, question);
    }


    @Override
    public QuestionResponse searchQuestionQuiz(int question_id) {
        List<QuizRes> quiz = quizDao.searchQuiz(question_id);
        if (quiz == null) {
            return new QuestionResponse(404, "找不到所有題目列表資訊");
        }
        return new QuestionResponse(200, quiz);
    }

    @Override
    public QuestionResponse searchAllPublish() {
        List<Question> res = QuestionDao.getAllPublish();
        if (res == null) {
            return new QuestionResponse(404, "找不到所有題目");
        }
        return new QuestionResponse(200, res);
    }

    @Override
    public QuestionResponse searchAllQuiz() {
        List<QuizRes> res = quizDao.searchAllQuiz();
        if (res == null) return new QuestionResponse(404, "找不到所有題目列表資訊");
        for (QuizRes item : res) {
            if (item == null) {
                return new QuestionResponse(404, "找不到題目列表資訊");
            }
        }
        return new QuestionResponse(200, res);
    }
}
