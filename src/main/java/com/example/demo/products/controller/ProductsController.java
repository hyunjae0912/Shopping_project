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
	UserRepository userRepository;
	
	@GetMapping("/list")
	public void list(Model model, Principal principal) {
	    List<ProductsDto> list = service.getList();
	    String name = (principal != null) ? principal.getName() : "게스트";
	    
	    System.out.println("이름 : " + name);
	    model.addAttribute("name", name);
	    model.addAttribute("list", list);
	}
	
	
	// 보기를 누르면 상세화면을 넘어감
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int productid, Model model) {
		ProductsDto dto = service.read(productid);
		model.addAttribute("dto", dto);
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam(name = "no") int productid) {
		
		ProductsDto dto = service.read(productid);
		
        String realPathOldImg = 
        "D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\" + dto.getImgUrl();
        
        String realPathOldDesImg =
        "D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\" + dto.getDesImg();
		
        File oldImgFile = new File(realPathOldImg);
        File oldDesFile = new File(realPathOldDesImg);
        
        if (oldImgFile.exists()) {
            oldImgFile.delete();
        }
        
        if(oldDesFile.exists()) {
        	oldDesFile.delete();
        }
        
		// DB에서 지우기
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
    		@RequestParam("imgUrl") MultipartFile imgUrl,
    		@RequestParam("desImg") MultipartFile desImg){
    	
		try {
	    	
	    	ProductsDto dto = service.read(productid);
	    	
	    	
	    	String imgPath = dto.getImgUrl();
	    	String desPath = dto.getDesImg();
	        // 새 이미지가 업로드된 경우에만 처리
	    	
	        if (imgUrl != null && !imgUrl.isEmpty()) {
	            // 기존 파일 삭제
	            String realPathOldImg = 
	            		"D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\" + dto.getImgUrl();
	            
	            File oldImgFile = new File(realPathOldImg);
	            if (oldImgFile.exists()) {
	                oldImgFile.delete(); // 삭제
	            }
	            
	            String uploadImgPath = 
	            "D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\imgUrl\\";
	            File saveFileImg = new File(uploadImgPath + imgUrl.getOriginalFilename());
	            saveFileImg.getParentFile().mkdirs();
	            imgUrl.transferTo(saveFileImg);

	            imgPath = "/imgUrl/" + imgUrl.getOriginalFilename(); // 새로운 경로로 변경
	        }
	        

	        if (desImg != null && !desImg.isEmpty()) {
	        	// 기존 파일 삭제
	        	String realPathOldImg = 
	        	"D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\" + dto.getDesImg();
	        	
	        	File oldImgFile = new File(realPathOldImg);
	        	if(oldImgFile.exists()) {
	        		oldImgFile.delete();
	        	}
	        	
	            String uploadDesPath = 
	            "D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\desUrl\\";
	            File saveFileDes = new File(uploadDesPath + desImg.getOriginalFilename());
	            saveFileDes.getParentFile().mkdirs();
	            desImg.transferTo(saveFileDes);

	            desPath = "/desUrl/" + desImg.getOriginalFilename(); // 새로운 경로로 변경
	        }
	        
	        String username = dto.getUser();
	        
	        ProductsDto newDto = ProductsDto
	        		.builder()
	        		.productid(productid)	// 빠져있었음
	        		.name(name)
	        		.price(price)
	        		.imgUrl(imgPath)
	        		.desImg(desPath)
	        		.user(username)
	        		.build();
	        
	        service.modify(newDto);
	        
	        System.out.println("가격 : " + price);
	        System.out.println("상품이름 : " + name);
	        System.out.println("유저 이름 : " + username);
	        System.out.println("이미지 경로 : " + imgPath);
	        System.out.println("설명 이미지 경로 : " + desPath);
	        
	        
	    	
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
    		Principal principal) throws IOException {
    	
    	String userNameSecurity = principal.getName();
    	   if (!imgUrl.isEmpty() && !desImg.isEmpty()) {
    	        // 절대 경로로 저장
    	        String uploadimgUrl = 
    	        "D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\imgUrl\\";
    	        
    	        String uploaddesImg = 
    	    	"D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\desUrl\\";
    	    	        
    	        
    	        File saveFileImg = new File(uploadimgUrl + imgUrl.getOriginalFilename());
    	        File saveFileDes = new File(uploaddesImg + desImg.getOriginalFilename());
   	        
    	        saveFileImg.getParentFile().mkdirs();
    	        saveFileDes.getParentFile().mkdirs();
    	        imgUrl.transferTo(saveFileImg);
    	        desImg.transferTo(saveFileDes);
    	        	
    	        // 저장된 파일의 상대 경로를 DB에 저장
    	        String filePathimgUrl = "/imgUrl/" + imgUrl.getOriginalFilename(); // 상대 경로로 변경
    	        String filePathdesUrl = "/desUrl/" + desImg.getOriginalFilename();
    	        String username = userService.read(userNameSecurity).getUserName();
    	        
    	        ProductsDto dto = ProductsDto
    	        		.builder()
    	        		.user(username)
    	        		.name(name)
    	        		.price(price)
    	        		.imgUrl(filePathimgUrl)
    	        		.desImg(filePathdesUrl)
    	        		.build();
    	        
    	        service.register(dto);
    	    }

    	    return "redirect:/products/list"; // 저장 후 다시 폼으로 이동
    }
}
