package com.example.demo.products.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.products.dto.ProductsDto;
import com.example.demo.products.repository.ProductRepository;
import com.example.demo.products.service.ProductService;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;


@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/list")
	public String list(@RequestParam(value = "keyword", required = false) String keyword,
	    Model model, Principal principal) {

	    String name = (principal != null) ? principal.getName() : "게스트";
	    List<ProductsDto> list;
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        // 서비스에 검색로직 추가
	        list = productService.searchByKeyword(keyword);
	    } else {
	        list = productService.getList();
	    }
	    model.addAttribute("list", list);
	    model.addAttribute("name", name);
	    model.addAttribute("keyword", keyword); // 검색어 유지용
	    return "products/list";
	}
	
	@GetMapping("/status")
	public String status() {
		
		
		return null;	
	}
	
	
	// 보기를 누르면 상세화면을 넘어감
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int productid, Model model, Principal principal) {
		ProductsDto dto = service.read(productid);
	    String name = (principal != null) ? principal.getName() : "게스트";
		
		model.addAttribute("dto", dto);
		model.addAttribute("name", name);
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam(name = "no") int productid) {
	    ProductsDto dto = service.read(productid);

	    // 실제 파일 경로는 "C:\\project_shoppting\\imgUrl\\파일명" 형식
	    String imgFileName = new File(dto.getImgUrl()).getName();
	    String desFileName = new File(dto.getDesImg()).getName();

	    String realPathOldImg = "C:\\project_shoppting\\imgUrl\\" + imgFileName;
	    String realPathOldDesImg = "C:\\project_shoppting\\desUrl\\" + desFileName;

	    File oldImgFile = new File(realPathOldImg);
	    File oldDesFile = new File(realPathOldDesImg);

	    if (oldImgFile.exists()) {
	        oldImgFile.delete();
	    }

	    if (oldDesFile.exists()) {
	        oldDesFile.delete();
	    }

	    // DB에서 삭제
	    service.remove(productid);
	    return "redirect:/";
	}

	
	
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int productid, Model model) {
		ProductsDto dto = service.read(productid);
		model.addAttribute("dto" ,dto);
	}
	
	@PostMapping("/modify")
	public String modifyChange(
	        @RequestParam("productid") int productid,
	        @RequestParam("name") String name,
	        @RequestParam("price") int price,
	        @RequestParam("count") int count,
	        @RequestParam("imgUrl") MultipartFile imgUrl,
	        @RequestParam("desImg") MultipartFile desImg) {

	    try {
	        ProductsDto dto = service.read(productid);

	        String imgPath = dto.getImgUrl();  // 기본값: 기존 이미지 경로
	        String desPath = dto.getDesImg();  // 기본값: 기존 상세이미지 경로

	        // 1. 이미지 변경 시 기존 파일 삭제 후 새 파일 저장
	        if (imgUrl != null && !imgUrl.isEmpty()) {
	            // 기존 파일 삭제 (실제 파일 경로)
	            String oldImgFilePath = "C:\\project_shoppting\\imgUrl\\" + 
	                new File(dto.getImgUrl()).getName();
	            File oldImgFile = new File(oldImgFilePath);
	            if (oldImgFile.exists()) {
	                oldImgFile.delete();
	            }
	            // 새 파일 저장
	            String imgFileName = imgUrl.getOriginalFilename();
	            String saveImgPath = "C:\\project_shoppting\\imgUrl\\" + imgFileName;
	            imgUrl.transferTo(new File(saveImgPath));
	            imgPath = "/uploadImg/" + imgFileName;
	        }

	        // 2. 설명이미지 변경 시 기존 파일 삭제 후 새 파일 저장
	        if (desImg != null && !desImg.isEmpty()) {
	            String oldDesFilePath = "C:\\project_shoppting\\desUrl\\" +
	                new File(dto.getDesImg()).getName();
	            File oldDesFile = new File(oldDesFilePath);
	            if (oldDesFile.exists()) {
	                oldDesFile.delete();
	            }
	            String desFileName = desImg.getOriginalFilename();
	            String saveDesPath = "C:\\project_shoppting\\desUrl\\" + desFileName;
	            desImg.transferTo(new File(saveDesPath));
	            desPath = "/uploadDes/" + desFileName;
	        }

	        String username = dto.getUser();

	        ProductsDto newDto = ProductsDto
	                .builder()
	                .productid(productid)
	                .name(name)
	                .price(price)
	                .imgUrl(imgPath)
	                .desImg(desPath)
	                .user(username)
	                .count(count)
	                .build();

	        service.modify(newDto);

	        System.out.println(count);
	        return "redirect:/products/list";
	    } catch (Exception e) {
	        System.out.println("error : " + e);
	        return null;
	    }
	}

	
    @GetMapping("/register")
    public String registerForm(){
        return "products/register"; // templates/products/register.html
    }

    @PostMapping("/register")
    public String registerHandler(
        @RequestParam("name") String name,
        @RequestParam("price") int price,
        @RequestParam("imgUrl") MultipartFile imgUrl,
        @RequestParam("desImg") MultipartFile desImg,
        @RequestParam("count") int count,
        Principal principal) throws IOException {

        String userNameSecurity = principal.getName();

        if (!imgUrl.isEmpty() && !desImg.isEmpty()) {
            // 실제 파일 저장 경로
            String uploadImgPath = "C:\\project_shoppting\\imgUrl\\";
            String uploadDesPath = "C:\\project_shoppting\\desUrl\\";

            String imgFileName = imgUrl.getOriginalFilename();
            String desFileName = desImg.getOriginalFilename();

            File saveFileImg = new File(uploadImgPath + imgFileName);
            File saveFileDes = new File(uploadDesPath + desFileName);

            saveFileImg.getParentFile().mkdirs();
            saveFileDes.getParentFile().mkdirs();

            imgUrl.transferTo(saveFileImg);
            desImg.transferTo(saveFileDes);

            // DB에 저장할 상대경로 (리소스핸들러와 매칭)
            String dbImgUrl = "/uploadImg/" + imgFileName;
            String dbDesUrl = "/uploadDes/" + desFileName;

            String username = userService.read(userNameSecurity).getUserName();

            ProductsDto dto = ProductsDto
                    .builder()
                    .user(username)
                    .name(name)
                    .price(price)
                    .imgUrl(dbImgUrl)
                    .desImg(dbDesUrl)
                    .count(count)
                    .build();

            service.register(dto);
        }

        return "redirect:/products/list";
    }

}
