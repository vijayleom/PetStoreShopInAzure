package com.cosmos.store.order.details.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Container(containerName = "petstore", ru = "400")
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CartOrderInfo {
	@Id
    private String sessionId;
	
	@PartitionKey
	private String orders;
}
