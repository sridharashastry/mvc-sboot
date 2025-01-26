package com.bring.inventory;

import com.bring.inventory.models.Inventory;
import com.bring.inventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){

		return
				args -> {


					System.out.println("Deleting all the existing data");
					// Delete all existing data
					inventoryRepository.deleteAll();



					System.out.println("Loading fresh data");

					Inventory inventory1 = new Inventory();
					inventory1.setSkuCode("iphone12");
					inventory1.setQuantity(100);
					System.out.println("Data: SKU Code = " + inventory1.getSkuCode() + ", Quantity = " + inventory1.getQuantity());

					Inventory inventory2 = new Inventory();
					inventory2.setSkuCode("iphone15");
					inventory2.setQuantity(0);
					System.out.println("Data: SKU Code = " + inventory2.getSkuCode() + ", Quantity = " + inventory2.getQuantity());

					inventoryRepository.save(inventory1);
					inventoryRepository.save(inventory2);

					System.out.println("Data is loaded into the table 't_inventory'. You need to test order-service to send orders");

				};
	}

}
