package com.ecobank.srms.repository;

import com.ecobank.srms.model.BioMedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BioMedDataRepository extends JpaRepository<BioMedData,Long>  {


  @Override
  Optional<BioMedData> findById(Long aLong);

  Optional<BioMedData> findBystudentId (Long id);

  Optional<BioMedData> findAllBystudentId(Long id);

  Optional<BioMedData> findByJambNo(String id);

  //BioMedData uploadPictureById(String picture);
}
