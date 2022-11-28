package com.ecobank.srms.repository;

import com.ecobank.srms.model.BioMedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface BioMedDataRepository extends JpaRepository<BioMedData,Long>  {


  @Override
  Optional<BioMedData> findById(Long aLong);

  Optional<BioMedData> findBystudentId (Long id);

  Optional<BioMedData> findAllBystudentId(Long id);

  Optional<BioMedData> findByJambNo(String id);



  //Optional<BioMedData> findpictureByjambNo(String jambNo);

  @Modifying
  @Query("update BioMedData b set b.picture = :imageUrl WHERE b.jambNo = :jambNo")
  void updatePictureByJambNo(String jambNo, String imageUrl);
}
