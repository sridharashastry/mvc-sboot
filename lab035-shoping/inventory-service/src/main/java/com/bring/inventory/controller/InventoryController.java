package com.bring.inventory.controller;


import com.bring.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
        public boolean validateStock(@PathVariable("sku-code")  String skuCode){


        return inventoryService.validateStock(skuCode);

    }
}
