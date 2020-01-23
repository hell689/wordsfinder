package net.wth.wordsfinder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public ModelAndView uploadPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload.html");
        return modelAndView;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            System.out.println("Dir created: " + uploadDir.getAbsolutePath());
            file.transferTo(new File(uploadDir.getAbsolutePath() + "/" + file.getOriginalFilename()));
            System.out.println("File saved");
        }
        return "redirect:/";
    }
}
