package com.cy.jpg;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cy.pojo.JsonResult;

import java.io.*;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/*import javax.servlet.http.HttpServletRequest;*/

/**
 * 文件上传
 */
@Controller
public class FileController {

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D:\\java\\p3-space\\CGB-DB-SYS-V3.3\\src\\main\\resources\\static\\dist\\img\\"; // 上传后的路径

        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename =  fileName;
        
//        System.out.println("fiklr");
        
        
        
        
        
       
        model.addAttribute("filename", "dist/img/"+filename);
        return "forward:index";
    }
}
