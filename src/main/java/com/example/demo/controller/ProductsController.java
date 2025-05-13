package com.example.demo.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	// 이미지 파일에 저장 성공 이제 이 경로를 DB에만 넣으면 됨
	
    @GetMapping("/register")
    public String registerForm() {
        return "products/register"; // templates/products/register.html
    }

    @PostMapping("/register")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String uploadDir =
            "D:\\img\\";
            File saveFile = new File(uploadDir + file.getOriginalFilename());

            // 폴더가 없으면 생성
            saveFile.getParentFile().mkdirs();
            file.transferTo(saveFile);

            System.out.println("파일 저장 위치: " + saveFile.getAbsolutePath());
        }

        return "redirect:/products/register"; // 저장 후 다시 폼으로 이동
    }
}
