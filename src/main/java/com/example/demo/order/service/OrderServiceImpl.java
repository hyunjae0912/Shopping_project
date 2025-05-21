package com.example.demo.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.entity.Order;
import com.example.demo.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository repository;
	
	
	
	@Override
	public int register(OrderDto dto) {
		System.out.println(dto);
		
		Order entity = dtoToEntity(dto);
		
		repository.save(entity);
		int newNum = entity.getOrderId();
		
		System.out.println(entity);
		System.out.println(newNum);
		
		return newNum;
	}

	@Override
	public OrderDto read(int no) {
		// 주문번호를 따라 찾음
		Optional<Order> optional = repository.findById(no);
		
		if(optional.isPresent()) {
			Order order = optional.get();
			OrderDto dto = entityToDto(order);
			
			return dto;
		}
		else {
			return null;			
		}
	}

	@Override
	public List<OrderDto> getAllList() {
		List<Order> result = repository.findAll();
		List<OrderDto> list = new ArrayList<>();
		
		list = result.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		
		return list;
	}
	
	@Override
	public List<OrderDto> getUserList(String username) {
		
		List<Order> resutList = repository.findOrdersByUsername(username);
		
		List<OrderDto> list = new ArrayList<>();
		
		list = resutList.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		
		return list;
	}

	@Override
	public void updateStatus(int orderId, String status) {
		
		// 상태의 기본값은 false이니 상태 업데이트를 하면 무조건 true로 바뀌게 하기
		Optional<Order> result = repository.findById(orderId);
		
		System.out.println(result);
		
		if(result.isPresent()) {
			
			Order entity = result.get();
			entity.setStatus(status);
			// 상태만 바꾼다.
			System.out.println(entity);
			repository.save(entity);
			
		}		
	}


}
