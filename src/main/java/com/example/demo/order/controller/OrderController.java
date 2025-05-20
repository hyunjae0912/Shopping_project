package com.example.demo.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderRepository repository;

	@Autowired
	OrderService service;
	
	@Autowired
	CartRepository cartRepository;

	// Principal principal
    @GetMapping("/main")
    public String orderMain(@RequestParam("userName") String userName, Model model) {

        List<Cart> cartList = cartRepository.findByUser_UserName(userName);

        model.addAttribute("cartList", cartList);
        model.addAttribute("userName", userName);

        return "order/main";
    }
    
    @GetMapping("/register")
    public String orderRegiser(
    		@RequestParam("userName") String userName,
    		@RequestParam("totalPrice") int totalPrice,
    		Model model
    		) {
    	
    	// 이름에 있는 값 찾기
    	List<Cart> list = cartRepository.findByUser_UserName(userName);
    	
    	for(int i = 0; i < list.size(); i++) {
    		System.out.println(list.get(i));
    	}
    	// 값 보내기
    	model.addAttribute("userName", userName);
    	model.addAttribute("totalPrice", totalPrice);
    	model.addAttribute("list", list);
    	
    	System.out.println("username : " + userName);
    	System.out.println("totalPrice : " + totalPrice);
    	
    	return null;
    	
    }
    
    @PostMapping("/register")
    public String orderRegisterPost(
    		@RequestParam("userName") String userName,
    		@RequestParam("totalPrice") int totalPrice,
    		@RequestParam("addr") String addr,
    		
    		Model model) {
    	
    	// 일단 값이 들어왔는지만 확인하기
    	/* 잘 들어옴
    	addr : 남동구 구월로 192
		username : 둘리
		totalPrice : 48000
    	 */
    	
    	System.out.println("addr : " + addr);
    	System.out.println("username : " + userName);
    	System.out.println("totalPrice : " + totalPrice);
    	
    	
    	// 내일(05-20) 들어온 데이터 정제해서 출력 후 다시 저장하기
    	
    	/* 
    		OrderDto dto = OrderDto.builder( )
			.addr("인천광역시 남동구")
			.status("주문 확인중")
			.totalPrice(20000)
			.user("홍길동").build();
    	 */
    	return "order/main";
    }

}
