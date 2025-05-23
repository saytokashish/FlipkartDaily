package services;

public interface IInventoryService {
    public void addInventory(String category,String brand,int quantity);
    public void printInventories();
}
