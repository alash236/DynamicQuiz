package com.example.quiz15.dao.feedback;

import com.example.quiz15.entity.feedback.FeedBack;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedBackDao extends JpaRepository<FeedBack,Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into feedback (question_id,name,email,writetime) values " +
            "(:question_id,:name,:email,:writetime)",nativeQuery = true)
    public void addFeedBack(int question_id, String name, String email, LocalDateTime writetime);

    @Query(value = "select * from feedback where question_id = :question_id order by writetime desc",nativeQuery = true)
    public List<FeedBack> searchSingleAllFeedBack(int question_id);

    @Query(value = "select * from feedback where email = :email",nativeQuery = true)
    public List<FeedBack> searchAllFeedBackEmail(String email);

    @Modifying
    @Transactional
    @Query(value ="delete from feedback where question_id = :question_id" ,nativeQuery = true)
    public void deleteFeedBack(int question_id);

    @Modifying
    @Transactional
    @Query(value = "update feedback set " +
            "writetime = :writetime " +
            "where question_id = :question_id and email = :email" ,nativeQuery = true)
    public void updateFeedBack(int question_id,String email,LocalDateTime writetime);

}
