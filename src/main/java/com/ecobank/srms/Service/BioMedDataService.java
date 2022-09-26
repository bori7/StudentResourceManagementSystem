package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.BioMedData;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface BioMedDataService {

    BioMedDataResponse edit(BioMedDataRequest bioMedDataRequest) throws IOException;
    BioMedDataResponse save(MultipartFile bioMedpic ,BioMedDataRequest bioMedDataRequest) throws IOException;

    BioMedDataResponse update(BioMedDataRequest bioMedDataRequest) throws IOException;

   ProfileResponse display(BioMedDataRequest bioMedDataRequest) throws Exception;

    File storeImage(MultipartFile img, String Filecat) throws IOException;

    BioMedDataResponse upload(MultipartFile bioMedDataRequest , String no)throws IOException;
    BioMedDataResponse saveProfile(BioMedDataRequest bioMedDataRequest);

    DisplayPictureResponse displayPic(DisplayPictureRequest displayPictureRequest) throws IOException;

}
