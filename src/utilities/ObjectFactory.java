package utilities;

import models.Item;
import services.IInventoryService;
import services.IItemService;
import services.impl.InventoryService;
import services.impl.ItemService;

public class ObjectFactory {

    public static IItemService getItemService() {
        return new ItemService();
    }

    public static IInventoryService getInventoryService() {
        return new InventoryService();
    }

    public static Item getItem(String brand, String category, int price){
        return new Item(brand, category, price);
    }
    // In future, you can add more services here
}

