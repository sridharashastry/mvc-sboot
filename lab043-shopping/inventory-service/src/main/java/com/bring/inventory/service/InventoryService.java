package com.bring.inventory.service;

import com.bring.inventory.dto.InventoryResponse;
import com.bring.inventory.models.Inventory;
import com.bring.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean validateStock(String skuCode){

        return inventoryRepository.findBySkuCode(skuCode).isPresent();


    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> validateStockUsingSkuCodeList(List<String> skuCode){

        log.info("wait started");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        log.info("wait ended");

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .inStock(inventory.getQuantity() > 0)
                            .build()
                )
                .toList();


    }

    public List<Inventory> getInventoryItems() {

        return inventoryRepository.findAll();

    }
}
