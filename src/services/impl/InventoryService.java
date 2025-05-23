package services.impl;

import models.Item;
import models.ItemFilter;
import repositories.ItemRepo;
import services.IInventoryService;
import services.IItemService;
import utilities.ObjectFactory;

import java.util.List;

public class InventoryService implements IInventoryService {
    private final ItemRepo itemRepo= ItemRepo.getInstance();
    @Override
    public void addInventory(String category, String brand, int quantity) {
        IItemService itemService = ObjectFactory.getItemService();
        ItemFilter filter = ObjectFactory.getItemFilterBuilder()
                .brandList(List.of(brand))
                .categoryList(List.of(category))
                .build();

        List<Item> items = itemService.searchItem(filter);
        for (Item item : items) {
            int updatedQuantity=item.getQuantity() + quantity;
            itemRepo.update(item.getId(),updatedQuantity);
        }
    }

    @Override
    public void printInventories() {
        for (Item item : itemRepo.getItems()) {
            System.out.println("Category: " + item.getCategory() + ", Brand: " + item.getBrand() + ", Quantity: " + item.getQuantity());
        }
    }
}
