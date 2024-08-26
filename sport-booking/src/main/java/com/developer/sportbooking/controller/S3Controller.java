package com.developer.sportbooking.controller;

import com.developer.sportbooking.config.AwsConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class S3Controller {

    @GetMapping("/upload")
    public String viewHomePage() {
        return "fileUpload";
    }

    @PostMapping("/upload")
    public String handleUploadForm(Model model, String description,
                                   @RequestParam("file") MultipartFile multipart) {
        String fileName = description;

        System.out.println("Description: " + description);
        System.out.println("filename: " + fileName);

        String message = "";

        try {
            AwsConfig.uploadFile(description, multipart.getInputStream(),"payment_screenshots");
            message = "Your file has been uploaded successfully!";
        }
        catch (Exception ex) {
            message = "Error uploading file: " + ex.getMessage();
        }

        String file = "";
        try {
            file = AwsConfig.getImage(fileName, "payment_screenshots");
            message = "Your file has been retrieved successfully!";
        }
        catch (Exception ex) {
            message = "Error retrieving file: " + ex.getMessage();
        }

        model.addAttribute("message", message);
        model.addAttribute("imgAsBase64", file);

        return "imageRetrieval";
    }

}

