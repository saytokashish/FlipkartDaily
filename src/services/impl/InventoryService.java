package services.impl;

import models.Item;
import models.ItemFilter;
import repositories.ItemRepo;
import services.IInventoryService;
import services.IItemService;
import utilities.ObjectFactory;
import utilities.exceptions.ItemDoesNotExistException;

import java.util.List;

public class InventoryService implements IInventoryService  {
    private final ItemRepo itemRepo= ItemRepo.getInstance();
    @Override
    public void addInventory (String category, String brand, int quantity) throws ItemDoesNotExistException{
        IItemService itemService = ObjectFactory.getItemService();
        ItemFilter filter = ObjectFactory.getItemFilterBuilder()
                .brandList(List.of(brand))
                .categoryList(List.of(category))
                .build();

        List<Item> items = itemService.searchItem(filter);
        if(items.isEmpty()){
            throw new ItemDoesNotExistException("Please add item first");
        }
        for (Item item : items) {
            int updatedQuantity=item.getQuantity() + quantity;
            item.setQuantity(updatedQuantity);
            itemRepo.update(item);
        }
    }

    @Override
    public void printInventories() {
        for (Item item : itemRepo.getItems()) {
            System.out.println("Category: " + item.getCategory() + ", Brand: " + item.getBrand() + ", Quantity: " + item.getQuantity());
        }
    }
}
