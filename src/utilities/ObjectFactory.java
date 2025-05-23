package utilities;

import models.Item;
import models.ItemFilter;
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

    public static ItemFilter.Builder getItemFilterBuilder(){
        return new ItemFilter.Builder();
    }
}

