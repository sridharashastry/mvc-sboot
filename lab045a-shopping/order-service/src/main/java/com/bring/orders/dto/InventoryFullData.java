package com.bring.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryFullData {
    private Long id;
    private String skuCode;
    private Integer quantity;
}