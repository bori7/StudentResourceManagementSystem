package com.ecobank.srms.repository;

import com.ecobank.srms.model.BioMedData;
import com.ecobank.srms.model.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BioMedDataRepository extends JpaRepository<BioMedData,Long>  {


  BioMedData findBystudentId (Long id);
  BioMedData findAllBystudentId(Long id);

  BioMedData findAllByJambNo(String id);

  //BioMedData uploadPictureById(String picture);
}
