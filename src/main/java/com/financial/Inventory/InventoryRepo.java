package com.financial.Inventory;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepo extends MongoRepository<ProductInventoryDetails, Integer> {

}
