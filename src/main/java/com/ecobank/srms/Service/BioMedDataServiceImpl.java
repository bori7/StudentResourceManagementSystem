package com.ecobank.srms.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.BioMedData;
import com.ecobank.srms.model.Student;
import com.ecobank.srms.repository.BioMedDataRepository;
import com.ecobank.srms.repository.StudentRepository;
import com.ecobank.srms.utils.ImageTrans;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Data
@Service
public class BioMedDataServiceImpl implements BioMedDataService {
    BioMedData bioMedData = new BioMedData();
    ModelMapper modelMapper= new ModelMapper();
    @Value("${pathName}")
    private String pathName;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BioMedDataRepository bioMedDataRepository;

    Logger logger = Logger.getLogger(StudentServiceImpl.class.getName());

    @Autowired
    private StudentService studentService;

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "bomacloudsdatabase",
            "api_key", "491885325678685",
            "api_secret", "WfwQjVpWwfcmChpsauayp-G_qBw",
            "secure", true));

    String proPicture;

    @Override

    public BioMedDataResponse save(BioMedDataRequest bioMedDataRequest) throws IOException {
        BioMedData bio = bioMedDataRepository.findByJambNo(bioMedDataRequest.getJambNo()).orElse(null);


        Student student = studentRepository.findPersonByJambNo(bioMedDataRequest.getJambNo()).orElse(null);

        if (student == null)
            return BioMedDataResponse.builder().message("Please use an existing JambNo").build();

        if (!(bio == null))
                return BioMedDataResponse.builder().message("Bio Data exists, Please update form").build();


        if(bioMedDataRequest.getPicture()==null || bioMedDataRequest.getPicture().equals("")){
            return BioMedDataResponse.builder().message("Please Upload a photo").build();
        }


        bioMedDataRequest.setPicture(new StringBuilder(uploadUri(bioMedDataRequest.getPicture().toString())));



            logger.info("First_Name " + bioMedDataRequest.getfName());
            logger.info("Surname"+bioMedDataRequest.getSurName());
            logger.info("Marital_status " +bioMedDataRequest.getmStatus());
            logger.info("Marital_status " +bioMedDataRequest.getAge());
            logger.info("Marital_status " +bioMedDataRequest.getAddress());


            modelMapper.map(bioMedDataRequest, bioMedData);
            bioMedDataRepository.save(bioMedData);
            return BioMedDataResponse.builder().message("Thank you").build();
    }

    public BioMedDataResponse update(BioMedDataRequest bioMedDataRequest) throws IOException {

        bioMedData = bioMedDataRepository.findByJambNo(bioMedDataRequest.getJambNo()).orElse(null);


        if (bioMedData == null)
            return BioMedDataResponse.builder().message("Please use an existing JambNo/save biodata").build();


        bioMedDataRequest.setStudentId(bioMedData.getStudentId());
        bioMedDataRequest.setPicture(new StringBuilder(uploadUri(bioMedDataRequest.getPicture().toString())));

            logger.info("First_Name " + bioMedDataRequest.getfName());
            logger.info("Surname"+bioMedDataRequest.getSurName());
            logger.info("Marital_status " +bioMedDataRequest.getmStatus());
            logger.info("Marital_status " +bioMedDataRequest.getAge());
            logger.info("Marital_status " +bioMedDataRequest.getAddress());
            logger.info("Marital_status " +bioMedDataRequest.getDateOfBirth());
            logger.info("Marital_status " +bioMedDataRequest.getEmail());
            logger.info("Marital_status " +bioMedDataRequest.getMidName());
            logger.info("Marital_status " +bioMedDataRequest.getFaculty());
            logger.info("Marital_status " +bioMedDataRequest.getJambNo());



            modelMapper.map(bioMedDataRequest, bioMedData);
            bioMedDataRepository.save(bioMedData);
            return BioMedDataResponse.builder().message("Form Updated").build();
    }

    @Override
    public ProfileResponse display(BioMedDataRequest bioMedDataRequest) throws IOException {
         BioMedData biodata = bioMedDataRepository.findByJambNo(bioMedDataRequest.getJambNo()).orElse(null);

         if(biodata==null){
             return ProfileResponse.builder().message("Please Fill BioData form").build();
         }

             ProfileResponse profileResponse = new ProfileResponse();
             modelMapper.map(biodata, profileResponse);
             return profileResponse.builder().message("Profile retrived")
                     .age(biodata.getAge())
                     .dateOfBirth(biodata.getDateOfBirth())
                     .lga(biodata.getLga())
                     .sex(biodata.getSex())
                     .midName(biodata.getMidName())
                     .faculty(biodata.getFaculty())
                     .fName(biodata.getFName())
                     .surName(biodata.getSurName())
                     .stOfOrg(biodata.getStOfOrg())
                     .department(biodata.getDepartment())
                     .picture(biodata.getPicture())
                     .build();

    }

    @Override
    public File storeImage(MultipartFile img, String filecat) throws IOException{
        String basepath = System.getProperty("user.dir");
        String uploadDir = "/uploads/";
        String realPathtouploads = basepath + uploadDir;


        if (!new File(realPathtouploads).exists())
        {
            new File(realPathtouploads).mkdir();
        }
        String[] filename = img.getOriginalFilename().split("[.]+");
        String suffix = filename[filename.length-1];
        String filePath = realPathtouploads + filecat + "." + suffix;
        logger.info("File suffix: " + suffix);
        logger.info("File path: " + filePath);
        File dest = new File(filePath);
        BufferedImage imgReal = ImageIO.read(new ByteArrayInputStream(img.getBytes()));
        ImageIO.write(imgReal,"png",new File(filePath));

        return dest;
    }

    public File CompressJpgImage(File input) throws IOException {
        String basePath = System.getProperty("user.dir");
        BufferedImage image = ImageIO.read(input);
        String compressedImagePath = basePath + "/uploads/" + "combinedCompressed.png";
        File compressedImageFile = new File(compressedImagePath);
        OutputStream os = new FileOutputStream(compressedImageFile);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios); ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); param.setCompressionQuality(0.5f);// Change the quality value you prefer
        writer.write(null, new IIOImage(image, null, null), param); os.close(); ios.close(); writer.dispose();
         return compressedImageFile;
         }


    public String uploadUri(String Uri) throws IOException{
        MultipartFile image = ImageTrans.base64ToMultipart(Uri);
        String picture;
        File file = storeImage(image,"biopic" );
        file = CompressJpgImage(file);
        Map uploadResult = cloudinary.uploader().upload(file,ObjectUtils.emptyMap());
        picture = String.valueOf(uploadResult.get("url"));
        return picture;
    }


    @Override
    public BioMedDataResponse upload(UploadPictureRequest uploadPictureRequest) throws IOException {


            BioMedData biodata = bioMedDataRepository.findByJambNo(uploadPictureRequest.getJambNo()).orElse(null);

            if(biodata==null){
                return BioMedDataResponse.builder().message("Please Fill in BioData Form").build();
            }


            String picture1  = uploadUri(uploadPictureRequest.getPicture());
            bioMedData.setJambNo(uploadPictureRequest.getJambNo());
            bioMedDataRepository.updatePictureByJambNo(uploadPictureRequest.getJambNo(),picture1);


            return BioMedDataResponse.builder().message("Picture Uploaded")
                    .url(biodata.getPicture())
                    .jambNo(biodata.getJambNo())
                    .build();
    }

    public String getPic(String jambNo){
        String url = null;
        Optional<BioMedData> bioMedData1 = bioMedDataRepository.findByJambNo(jambNo);
        if (!bioMedData1.isPresent()){
            return "Student does not exist";
        }
        url = bioMedData1.get().getPicture();


        return url;
    }

    @Override
    public DisplayPictureResponse displayPic(DisplayPictureRequest displayPictureRequest) throws IOException {
        boolean ispresent = bioMedDataRepository.findByJambNo(displayPictureRequest.getJambNo()).isPresent();

        if (!ispresent){
            return DisplayPictureResponse.builder().message("Cannot display picture to unknown JambNo").build();
        }

       String url = getPic(displayPictureRequest.getJambNo());

        if (url == null){
           return DisplayPictureResponse.builder().message("no picture present").build();
        }
        return DisplayPictureResponse.builder().message("Picture Displayed").picUrl(url).code("00").build();
    }
}