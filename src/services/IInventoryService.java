package services;

import utilities.exceptions.ItemDoesNotExistException;

public interface IInventoryService {
    public void addInventory(String category,String brand,int quantity) throws ItemDoesNotExistException;
    public void printInventories();
}
