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
import com.example.demo.cart.service.CartService;
import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.service.OrderService;
import com.example.demo.orderItem.service.OrderItemService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderRepository repository;

	@Autowired
	OrderService service;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderItemService orderItemService;

	// Principal principal
    @GetMapping("/complete")
    public String orderMain(@RequestParam("userName") String userName, Model model) {
    	
    	// 메인에서 보일 예정
    	
        List<Cart> list = cartRepository.findByUser_UserName(userName);

        model.addAttribute("list", list);
        model.addAttribute("userName", userName);

        return "order/complete";
    }
    
    @GetMapping("/register")
    public String orderRegiser(
    		@RequestParam("userName") String userName,
    		@RequestParam("totalPrice") int totalPrice,
    		Model model) {
    	
    	// 이름에 있는 값 찾기
    	// 서비스로 옮겨야함
    	List<Cart> list = cartRepository.findByUser_UserName(userName);
    	
//    	for(int i = 0; i < list.size(); i++) {
//    		System.out.println(list.get(i));
//    	}
    	
    	// 값 보내기
    	model.addAttribute("userName", userName);
    	model.addAttribute("totalPrice", totalPrice);
    	model.addAttribute("list", list);
    	
    	
    	return null;
    	
    }
    
    @PostMapping("/register")
    public String orderRegisterPost(
    		@RequestParam("userName") String userName,
    		@RequestParam("totalPrice") int totalPrice,
    		@RequestParam("addr") String addr,
    		Model model) {
    	
    	String defaultStatus = "주문 확인중";
    	
    	OrderDto dto = OrderDto.builder()
    			.addr(addr)
    			.status(defaultStatus)
    			.totalPrice(totalPrice)
    			.user(userName)
    			.build();
    	
    	int testNum = service.register(dto);
    	System.out.println(testNum);
    	
    	// 바로 저장함
    	orderItemService.register(userName, testNum);
    	
    	return "order/complete";
    }

}
