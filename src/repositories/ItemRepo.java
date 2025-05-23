package repositories;

import models.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepo {
    private final static Map<Integer,Item> items=new HashMap<>();

    private static repositories.ItemRepo instance;
    private ItemRepo() {}
    public static ItemRepo getInstance() {
        if (instance == null) {
            instance = new ItemRepo();
        }
        return instance;
    }

    public void addItem(Item item) {
        items.put(item.getId(),item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    public void update(Item item){
        if(items.containsKey(item.getId())){
            items.put(item.getId(),item);
        }
    }

}