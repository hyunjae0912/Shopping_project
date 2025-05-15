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
	
	// 이미지 파일에 저장 성공 이제 이 경로를 DB에만 넣으면 됨
	
	@Autowired
	ProductService service;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/list")
	public void list(Model model) {
		List<ProductsDto> list = service.getList();
		
		model.addAttribute("list", list);
	}
	
	// 보기를 누르면 상세화면을 넘어감
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int productid, Model model) {
		ProductsDto dto = service.read(productid);
		model.addAttribute("dto", dto);
	}
	
//	@GetMapping("/modify")
//	public void modify() {
//		
//	}
	
	//Principal principal
	
    @GetMapping("/register")
    public String registerForm() {
        return "products/register"; // templates/products/register.html
    }

    // 유저 아이디 받아오는 건 principal를 사용해서 user 가져오기
    // 일단은 지금은 임시 아이디로 사용할 예정
    @PostMapping("/register")
    public String registerHandler(
    		@RequestParam("name") String name, 
    		@RequestParam("price") int price,
    		@RequestParam("imgUrl") MultipartFile imgUrl,
    		@RequestParam("desUrl") MultipartFile desUrl) throws IOException {
    	   if (!imgUrl.isEmpty() && !desUrl.isEmpty()) {
    	        // 절대 경로로 저장
    	        String uploadimgUrl = 
    	        "D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\imgUrl\\";
    	        
    	        String uploaddesImg = 
    	    	"D:\\hyunjae\\workspace\\Shopping_Project\\src\\main\\resources\\static\\desUrl\\";
    	    	        
    	        
    	        File saveFileImg = new File(uploadimgUrl + imgUrl.getOriginalFilename());
    	        File saveFileDes = new File(uploaddesImg + desUrl.getOriginalFilename());
    	        
    	        System.out.println(imgUrl.getOriginalFilename());
    	        System.out.println(desUrl.getOriginalFilename());
    	        
    	        saveFileImg.getParentFile().mkdirs();
    	        saveFileDes.getParentFile().mkdirs();
    	        imgUrl.transferTo(saveFileImg);
    	        desUrl.transferTo(saveFileDes);
    	        

    	        // 파일이 저장된 위치 출력
    	        System.out.println("물건 사진 이미지 저장 위치: " + saveFileImg.getAbsolutePath());

    	        // 저장된 파일의 상대 경로를 DB에 저장
    	        String filePathimgUrl = "/imgUrl/" + imgUrl.getOriginalFilename(); // 상대 경로로 변경
    	        String filePathdesUrl = "/desUrl/" + desUrl.getOriginalFilename();
    	        
    	        // 값을 잘 들어옴
    	        System.out.println(filePathimgUrl);
    	        System.out.println(filePathdesUrl);
    	        System.out.println(name);
    	        System.out.println(price);
    	        
    	        
    	        String username = userService.read("둘리").getUserName();
    	        System.out.println(username);
    	        
    	        ProductsDto dto = ProductsDto
    	        		.builder()
    	        		.user(username)
    	        		.name(name)
    	        		.price(price)
    	        		.imgUrl(filePathimgUrl)
    	        		.desImg(filePathdesUrl)
    	        		.build();
    	        
    	        service.register(dto);
    	        
    	        
    	        // 나머진 로그인 기능을 만들고 
    	        // user이름이 로그인에 잘 나오나 확인하지
    	        
    	    }

    	    return "redirect:/products/list"; // 저장 후 다시 폼으로 이동
    }
}
