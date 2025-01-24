package com.bring.inventory.service;

import com.bring.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean validateStock(String skuCode){

        return inventoryRepository.findBySkuCode(skuCode).isPresent();


    }
}
