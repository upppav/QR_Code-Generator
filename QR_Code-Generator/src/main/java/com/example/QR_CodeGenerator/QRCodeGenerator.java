package com.example.QR_CodeGenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QRCodeGenerator {
    public static void main(String[] args) {

     int id=144;
     String name="Pavani";
     String address="Bangalore";
     String email="pav@gmail.com";
     long number=1226543242;

     String userData=

                "id :" + id +"\n"+
                "name :" + name + "\n" +
                " address :" + address + "\n" +
                "email :" + email + "\n" +
                "number :" + number
                ;
     String directory_path="QR_Codes";
     String filePath= directory_path+ File.separator+name+"qr_code.png";
        Path dir=FileSystems.getDefault().getPath(directory_path);
        if(!Files.exists(dir))
        {
            try {
                Files.createDirectories(dir);
                System.out.println("Directory created :"+dir);
            } catch (IOException e) {
                System.out.println("Error creating directory :"+e.getMessage());
                throw new RuntimeException(e);
            }
        }
        Map<EncodeHintType , Object> hintTypeObjectMap=new HashMap<>();
        hintTypeObjectMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        try {
            BitMatrix bit= new MultiFormatWriter().encode(userData, BarcodeFormat.QR_CODE,300,300,hintTypeObjectMap);
            MatrixToImageWriter.writeToStream(bit,"PNG",new FileOutputStream(filePath));
            System.out.println("Qr code generated successfully!");
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

