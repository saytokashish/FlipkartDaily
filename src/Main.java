import models.Item;
import models.ItemFilter;
import services.IInventoryService;
import services.IItemService;
import services.impl.InventoryService;
import services.impl.ItemService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        IItemService iItemService=new ItemService();
        IInventoryService iInventoryService = new InventoryService();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Flipkart Daily ---");
            System.out.println("1. Add Item");
            System.out.println("2. Add Inventory");
            System.out.println("3. Print Inventory");
            System.out.println("4. Search Item");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter price: ");
                    int price = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();

                    Item item = new Item(price, brand, category, quantity);
                    iItemService.addItem(item);
                    System.out.println("Item added successfully.");
                    break;

                case 2:
                    System.out.print("Enter category: ");
                    String invCategory = sc.nextLine();
                    System.out.print("Enter brand: ");
                    String invBrand = sc.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int addQuantity = sc.nextInt();

                    iInventoryService.addInventory(invCategory, invBrand, addQuantity);
                    break;

                case 3:
                    iInventoryService.printInventories();
                    break;

                case 4:
                    System.out.print("Enter brand to search (or press Enter to skip): ");
                    String brandInput = sc.nextLine();
                    List<String> searchBrands = null;
                    if (!brandInput.trim().isEmpty()) {
                        searchBrands = Arrays.stream(brandInput.split(","))
                                .map(String::trim)
                                .filter(s -> !s.isEmpty())
                                .collect(Collectors.toList());
                    }

                    System.out.print("Enter category to search (or press Enter to skip): ");
                    String categoryInput = sc.nextLine();
                    List<String> searchCategory=null;
                    if (!categoryInput.trim().isEmpty()){
                        searchCategory=Arrays.stream(categoryInput.split(","))
                                .map(String::trim)
                                .filter(s -> !s.isEmpty())
                                .collect(Collectors.toList());
                    }

                    System.out.print("Enter price from (or press Enter to skip): ");
                    String priceFromInput = sc.nextLine();
                    Integer priceFrom = null;
                    if (!priceFromInput.trim().isEmpty()) {
                        priceFrom = Integer.parseInt(priceFromInput);
                    }

                    System.out.print("Enter price to (or press Enter to skip): ");
                    String priceToInput = sc.nextLine();
                    Integer priceTo = null;
                    if (!priceToInput.trim().isEmpty()) {
                        priceTo = Integer.parseInt(priceToInput);
                    }

                    System.out.print("Enter order by field (price/quantity) or press Enter to skip: ");
                    String orderByField = sc.nextLine();
                    Function<Item, Comparable> orderByFunction = ItemFilter.getOrderByFunction(orderByField);

                    models.enums.Order order = null;
                    if (orderByFunction != null) {
                        System.out.print("Enter order (asc/desc): ");
                        String orderInput = sc.nextLine();
                        if (orderInput.equalsIgnoreCase("asc")) {
                            order = models.enums.Order.ASC;
                        } else if (orderInput.equalsIgnoreCase("desc")) {
                            order = models.enums.Order.DESC;
                        }
                    }


                    ItemFilter filter = new ItemFilter.Builder()
                            .brandList(searchBrands)
                            .categoryList(searchCategory)
                            .priceFrom(priceFrom)
                            .priceTo(priceTo)
                            .orderBy(orderByFunction)
                            .order(order)
                            .build();

                    List<Item> results = iItemService.searchItem(filter);

                    if (results.isEmpty()) {
                        System.out.println("No items found.");
                    } else {
                        for (Item resultItem : results) {
                            System.out.println("ID: " + resultItem.getId() +
                                    ", Brand: " + resultItem.getBrand() +
                                    ", Category: " + resultItem.getCategory() +
                                    ", Price: " + resultItem.getPrice() +
                                    ", Quantity: " + resultItem.getQuantity());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

}