package com.example.quiz15.dao.quiz;

import com.example.quiz15.entity.quiz.Quiz;
import com.example.quiz15.vo.question.QuizRes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into quiz (quiz_id,name,type,is_required,`option`,question_id) " +
            " values (:quiz_id,:name,:type,:is_required,:option,:question_id)",nativeQuery = true)
    public void addQuiz(int quiz_id,int question_id,String name,String type,boolean is_required,
                       String option);

    @Modifying
    @Transactional
    @Query(value = "delete from quiz where question_id = :question_id",nativeQuery = true)
    public void deleteQuiz(int question_id);

    @Query(value = "SELECT LAST_INSERT_ID();",nativeQuery = true)
    public int getLatestQuizId();

    @Query(value = "select quiz_id,name,type,is_required,`option` from quiz where question_id = :question_id",nativeQuery = true)
    public List<QuizRes> searchQuiz(int question_id);

    @Query(value = "select * from quiz",nativeQuery = true)
    public List<QuizRes> searchAllQuiz();


    @Query(value ="select `option` from quiz where question_id = :question_id and quiz_id = :quiz_id" ,nativeQuery = true)
    public String getQuizOption(int question_id,int quiz_id);

    @Query(value ="select type from quiz where question_id = :question_id and quiz_id = :quiz_id" ,nativeQuery = true)
    public String getQuizType(int question_id,int quiz_id);

    @Query(value ="select is_required from quiz where question_id = :question_id and quiz_id = :quiz_id" ,nativeQuery = true)
    public Byte getQuizIs_Required(int question_id,int quiz_id);

    @Query(value = "SELECT `option` FROM quiz WHERE question_id = :questionId AND quiz_id = :quizId",nativeQuery = true)
    public String getValidOptions(int questionId, int quizId);
}
