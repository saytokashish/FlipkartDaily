package services;

import models.Item;
import models.ItemFilter;
import utilities.exceptions.ItemAlreadyExistException;

import java.util.List;

public interface IItemService {
    public void addItem(Item item) throws ItemAlreadyExistException;
    public List<Item> searchItem(ItemFilter itemFilter);
}
