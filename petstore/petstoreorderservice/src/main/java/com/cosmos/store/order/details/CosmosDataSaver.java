package com.cosmos.store.order.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cosmos.store.order.details.entity.CartOrderInfo;
import com.cosmos.store.order.details.repository.OrderRepository;


@Component
public class CosmosDataSaver {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public void saveOrderDetails(String identifier, String Json) {
		CartOrderInfo coi = new CartOrderInfo();
		coi.setSessionId(identifier);
		coi.setOrders(Json);
		
		orderRepository.save(coi);
	}
}
