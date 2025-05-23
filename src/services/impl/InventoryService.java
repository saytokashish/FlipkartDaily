package services.impl;

import models.Item;
import models.ItemFilter;
import repositories.ItemRepo;
import services.IInventoryService;

import java.util.List;

public class InventoryService implements IInventoryService {
    private final ItemRepo itemRepo= ItemRepo.getInstance();
    @Override
    public void addInventory(String category, String brand, int quantity) {
        ItemService itemService = new ItemService();
        ItemFilter filter = new ItemFilter.Builder()
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
        itemRepo.printInventories();
    }
}
