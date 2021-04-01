package com.eepractice.webcrawller.controller;

import com.eepractice.webcrawller.bean.ReadInformation;
import com.eepractice.webcrawller.utils.ReaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    ReaderUtils readerUtils;

    /**
     * 生成随机文件名
     * @param filename
     * @return
     */
    private String generateRandomFilename(String filename){
        // 获得源文件后缀
        int extendPosition = filename.indexOf(".");
        String extension = filename.substring(extendPosition);
        // 随机的UUID格式为XXXXX-XXXXXX-XXXXX
        UUID uuidString = UUID.randomUUID();
        return uuidString.toString().replace("-","")+extension;
    }

    /**
     * 处理上传文件的controller
     * @param file
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String,Object>> uploadFile(@RequestParam("file") MultipartFile file){
        String filename = "";
        ReadInformation result = new ReadInformation();
        try {
            // 生成随机文件名
            filename = generateRandomFilename(file.getOriginalFilename());
            /**
             * windows下的文件保存路径
             */
            // 获取当前项目的路径
            //String projectPath = System.getProperty("user.dir");
//            String filePath = projectPath+"\\src\\main\\resources\\static\\files\\"+filename;

            /**
             * Linux下的文件保存路径
             */
            String filePath = "/usr/local/upload/"+filename;
            File dest = new File(filePath);
            // 保存文件
            file.transferTo(dest);
            System.out.println("file saved");
            if (filename.split("\\.")[1].trim().equals("txt")){
                result = readerUtils.readUploadedTxtFile(filePath);
            }else{
                result = readerUtils.readUploadedExcelFile(filePath);
            }
            //读取文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("result",result);
        return ResponseEntity.ok(responseMap);
    }
}
