package com.example.demo.orderItem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.cart.service.CartService;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.orderItem.dto.OrderItemDto;
import com.example.demo.orderItem.entity.OrderItem;
import com.example.demo.orderItem.repository.OrderItemRepository;
import com.example.demo.products.entity.Products;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final UserRepository userRepository;

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderRepository orderRepository;	
	
	@Autowired
	OrderItemRepository orderItemRepository;


    OrderItemServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	
	@Transactional
	@Override
	public List<OrderItemDto> register(String userName, int orderId) {
		
		// 저장 시작을 위해 카트에서 이름 가져옴
		List<Cart> cartList = cartRepository.findByUser_UserName(userName);
		
		// 주분 번호 가져옴
		Order order = orderRepository.findById(orderId).orElse(null);
		
		System.out.println(order);
		
		// orderitem에 저장하기 위한 리스트 생성
		List<OrderItemDto> list = new ArrayList<>();
		
		// 반복문을 사용해서 데이터 저장하기
		for(Cart cart : cartList) {
			// 카트에 있는 물거 가져오기
			Products products = cart.getProducts();
			
			OrderItem orderItem = OrderItem.builder()
					.status("주문 확인중")
					.order(order)
					.products(products)
					.build();
			
			orderItemRepository.save(orderItem);
			
	        OrderItemDto dto = entityToDto(orderItem);
	        list.add(dto);
		}
		
		cartRepository.deleteByUser_UserName(userName);
		
		
		return null;
	}
	
	// 카트에 있는 값 가져오기
	@Override
	public List<Cart> getListByUserName(String userName) {
		
        List<Cart> list = cartRepository.findByUser_UserName(userName);
		
		return list;
	}

	@Override
	public List<OrderItem> read(String name) {
		
		List<OrderItem> list = orderItemRepository.findByOrder_User_UserName(name);
		
	    return list;
	}

	@Override
	public List<OrderItem> readOnlySeller(String name) {
		
		List<OrderItem> list = orderItemRepository.findOrderItemsBySellerName(name);
		
		return list;
	}

	@Override
	public void updateStatus(int orderItemId, String status) {
		
		Optional<OrderItem> result = orderItemRepository.findById(orderItemId);
		
		// Optional.empty 
		System.out.println(result);
		
		if(result.isPresent()) {
			OrderItem entity = result.get();
			entity.setStatus(status);
			orderItemRepository.save(entity);
		}
	}
}