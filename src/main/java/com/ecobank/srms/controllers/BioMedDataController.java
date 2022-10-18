package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.BioMedDataService;
import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.BioMedData;
import com.ecobank.srms.repository.BioMedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Logger;


@RestController
@CrossOrigin
@RequestMapping("api/v1/student")
public class BioMedDataController {

        final Logger logger = Logger.getLogger(BioMedDataController.class.getName());

        @Autowired
        private BioMedDataService bioMedDataService;

        @PostMapping(value = "/save_biodata")
        public ResponseEntity save(@RequestBody BioMedDataRequest bioMedDataRequest) throws IOException{
            return ResponseEntity.ok(bioMedDataService.save(bioMedDataRequest));
        }

        @PutMapping(value = "/update_biodata")
        public ResponseEntity update(@RequestBody  BioMedDataRequest bioMedDataRequest) throws IOException{
            return ResponseEntity.ok(bioMedDataService.update(bioMedDataRequest));
        }

        @PostMapping(value = "/profile")
        public ResponseEntity display(@RequestBody BioMedDataRequest bioMedDataRequest) throws Exception {
                return ResponseEntity.ok(bioMedDataService.display(bioMedDataRequest));
        }

        @PostMapping(value = "/upload_biodata_picture")
        public ResponseEntity upload(@RequestParam("image")  MultipartFile bioMedPic , @RequestParam("jambNo")  String jambNo) throws IOException {
                return ResponseEntity.ok(bioMedDataService.upload(bioMedPic , jambNo));
        }

        @PostMapping(value = "/display_biodata_picture")
        public ResponseEntity displayPic(@RequestBody DisplayPictureRequest displayPictureRequest) throws IOException {
                return ResponseEntity.ok(bioMedDataService.displayPic(displayPictureRequest));
        }


}
