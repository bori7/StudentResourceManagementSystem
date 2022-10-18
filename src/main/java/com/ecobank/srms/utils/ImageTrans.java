package com.ecobank.srms.utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ImageTrans implements MultipartFile {
    private final byte[] fileContent;

    private final String extension;
    private final String contentType;


    /**
     * @param base64
     * @param dataUri     The format is similar to: data:image/png;base64
     */
    public ImageTrans(String base64, String dataUri) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = dataUri.split(";")[0].split("/")[1];
        this.contentType = dataUri.split(";")[0].split(":")[1];
    }

    public ImageTrans(String base64, String extension, String contentType) {
        this.fileContent = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
        this.extension = extension;
        this.contentType = contentType;
    }

    @Override
    public String getName() {
        return "param_" + System.currentTimeMillis();
    }

    @Override
    public String getOriginalFilename() {
        return "file_" + System.currentTimeMillis() + "." + extension;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContent);
        }
    }
    public static MultipartFile base64ToMultipart(String base64) {
//        try {
//            String[] baseStrs = base64.split(",");
//
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] b = new byte[0];
//            b = decoder.decodeBuffer(baseStrs[1]);
//
//            for (int i = 0; i < b.length; ++i) {
//                if (b[i] < 0) {
//                    b[i] += 256;
//                }
//            }
//            return new ImageTrans(b, baseStrs[0]);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
        final String[] base64Array = base64.split(",");
        String dataUir, data;
        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            //Build according to the specific file you represent
            dataUir = "data:image/jpg;base64";
            data = base64Array[0];
        }
        return new ImageTrans(data, dataUir);
//        return multipartFile;
    }

}



