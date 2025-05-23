package services;

import models.Item;
import models.ItemFilter;

import java.util.List;

public interface IItemService {
    public void addItem(Item item);
    public List<Item> searchItem(ItemFilter itemFilter);
}
