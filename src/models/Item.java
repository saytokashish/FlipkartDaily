package models;

public class Item {
    private static int counter=0;
    private final int id;
    private int price;
    private String brand;
    private String category;

    private int quantity;

    public Item(int price, String brand, String category, int quantity) {
        this.id = getNextId();
        this.price = price;
        this.brand = brand;
        this.category = category;
        this.quantity = quantity;
    }

    private int getNextId(){
        return ++counter;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
