package com.ecobank.srms.repository;

import com.ecobank.srms.model.CourseManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CourseManageRepository  extends JpaRepository<CourseManage, Long> {
     Optional<CourseManage> findById (Long Id);
     List<CourseManage> findByStudReg (String studReg);
     //CourseManage findById (Long Id);


     @Modifying
     @Query("delete from CourseManage c where c.course_Id = :Id and c.studReg = :studReg")
     void deleteBycourseId(Long Id,String studReg);


     @Modifying
     @Query("select c from CourseManage c where c.course_Id = :courseId and c.studReg = :studReg")
     List<CourseManage> findByCourseIdAndStudReg (Long courseId, String studReg);
}
