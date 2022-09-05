package com.ecobank.srms.repository;

import com.ecobank.srms.model.CourseManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseManageRepository  extends JpaRepository<CourseManage, Long> {
     Optional<CourseManage> findById (Long Id);
     List<CourseManage> findByStudReg (String studReg);
     //CourseManage findById (Long Id);
}
