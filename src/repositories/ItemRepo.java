package repositories;

import models.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    private List<Item> items;
    private static repositories.ItemRepo instance;
    private ItemRepo() {
        items=new ArrayList<>();
    }
    public static ItemRepo getInstance() {
        if (instance == null) {
            instance = new repositories.ItemRepo();
        }
        return instance;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void printInventories() {
        for (Item item : items) {
            System.out.println("Category: " + item.getCategory() + ", Brand: " + item.getBrand() + ", Quantity: " + item.getQuantity());
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void update(int id,int quantity){
        for (Item item : items) {
            if (item.getId() == id) {
                item.setQuantity(quantity);
                System.out.println("Item with id " + id + " updated successfully.");
                return ;
            }
        }
    }

}