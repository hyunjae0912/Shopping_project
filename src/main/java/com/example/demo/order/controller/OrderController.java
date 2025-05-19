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
    public String orderMain(@RequestParam("user") String userName, Model model) {

        List<Cart> cartList = cartRepository.findByUser_UserName(userName);

        model.addAttribute("cartList", cartList);
        model.addAttribute("user", userName);

        return "order/main";
    }
    
    @GetMapping("/register")
    public String orderRegister(@RequestParam("user") String userName) {
    	
    	/* 
    		OrderDto dto = OrderDto.builder( )
			.addr("인천광역시 남동구")
			.status("주문 확인중")
			.totalPrice(20000)
			.user("홍길동").build();
    	 */
    	return null;
    }

}
