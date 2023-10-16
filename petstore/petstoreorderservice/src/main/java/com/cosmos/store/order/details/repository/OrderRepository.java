package com.cosmos.store.order.details.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.cosmos.store.order.details.entity.CartOrderInfo;

@Repository
public interface OrderRepository extends CosmosRepository<CartOrderInfo, String> {
}