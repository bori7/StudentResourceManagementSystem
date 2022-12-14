package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.BioMedDataService;
import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.BioMedData;
import com.ecobank.srms.repository.BioMedDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
        public ResponseEntity save(@Valid @RequestBody BioMedDataRequest bioMedDataRequest) throws IOException{
                logger.info("biomedRequest : " +  bioMedDataRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(bioMedDataService.save(bioMedDataRequest));
        }


        @PutMapping(value = "/update_biodata")
        public ResponseEntity update(@Valid @RequestBody  BioMedDataRequest bioMedDataRequest) throws IOException{
            return ResponseEntity.ok(bioMedDataService.update(bioMedDataRequest));
        }

        @PostMapping(value = "/profile")
        public ResponseEntity displayProfile(@Valid @RequestBody BioMedDataRequest bioMedDataRequest) throws Exception {
                return ResponseEntity.ok(bioMedDataService.display(bioMedDataRequest));
        }



        @PostMapping(value = "/upload_biodata_picture")
        public ResponseEntity upload(@Valid @RequestBody UploadPictureRequest uploadPictureRequest) throws IOException {
                return ResponseEntity.ok(bioMedDataService.upload(uploadPictureRequest));
        }

//        @PostMapping(value = "/display_biodata_picture")
//        public ResponseEntity displayPic1(@Valid @RequestBody DisplayPictureRequest displayPictureRequest) throws IOException {
//                return ResponseEntity.ok(bioMedDataService.displayPic(displayPictureRequest));
//        }



        @Operation(summary = "Display Biodata Picture")
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Picture was displayed",
                        content = {
                                @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = BioMedData.class),
                                examples = @ExampleObject( value= "{'message':'Picture Displayed' , " +
                                        "'picUrl':'http://res.cloudinary.com/bomacloudsdatabase/image/upload/v1666082885/zqkiexvzkiwicovt8ew4.png', " +
                                        "'code' : '00'}")
                                )
                        }),
                @ApiResponse(responseCode = "404", description = "Invalid jambNo supplied/No Picture Present",
                        content = {@Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorResponseDTO.class),
                                examples = @ExampleObject( value= "{'message': 'Cannot display picture to unknown JambNo', " +
                                        "'responseCode' : '11'}")
                        )})
        }
        )
        @GetMapping(value = "/display_biodata_picture")
        public ResponseEntity displayPic(
                @Parameter(example = "4100003")
                @RequestParam String jambNo) throws IOException {
                return ResponseEntity.ok(bioMedDataService.displayPic(jambNo));
        }

}



