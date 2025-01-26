package com.bring.inventory.controller;


import com.bring.inventory.dto.InventoryResponse;
import com.bring.inventory.models.Inventory;
import com.bring.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;


    // curl -X GET "http://localhost:8082/api/inventory/iphone12"

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
        public boolean validateStock(@PathVariable("sku-code")  String skuCode){


        System.out.println("Inside validateStock with skucode: "+skuCode);
        return inventoryService.validateStock(skuCode);

    }




    //  curl -X GET "http://localhost:8082/api/inventory/items"

    /*

    curl -X GET "http://localhost:8082/api/inventory/items"
[{"id":33,"skuCode":"iphone12","quantity":100},{"id":34,"skuCode":"iphone15","quantity":0}]
PS C:\ws>

     */
    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getInventoryItems(){

        System.out.println("Inside the method getInventoryItems");

        List<Inventory> inventoryList = inventoryService.getInventoryItems();
        for (Inventory item : inventoryList) {
            System.out.println(item);
        }

        return inventoryList;

    }



    // curl "http://localhost:8082/api/inventory/list?skuCode=iphone12&skuCode=iphone15"

    /*
    PS C:\ws> curl "http://localhost:8082/api/inventory/list?skuCode=iphone12&skuCode=iphone15"
[{"skuCode":"iphone12","inStock":true},{"skuCode":"iphone15","inStock":false}]
PS C:\ws>


     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> validateStockUsingSkuCodeList(@RequestParam List<String> skuCode){


        System.out.println("incoming codes : "+skuCode.toString());


        return inventoryService.validateStockUsingSkuCodeList(skuCode);

    }



}
