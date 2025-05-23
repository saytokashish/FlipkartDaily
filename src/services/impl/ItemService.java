package services.impl;

import models.Item;
import models.ItemFilter;
import models.enums.Order;
import repositories.ItemRepo;
import services.IItemService;
import utilities.ObjectFactory;
import utilities.exceptions.ItemAlreadyExistException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ItemService implements IItemService {
    private ItemRepo itemRepo = ItemRepo.getInstance();

    @Override
    public void addItem(Item item) throws ItemAlreadyExistException {
        ItemFilter filter = ObjectFactory.getItemFilterBuilder()
                .brandList(List.of(item.getBrand()))
                .categoryList(List.of(item.getCategory()))
                .build();
        if(!this.searchItem(filter).isEmpty())
            throw new ItemAlreadyExistException("Item already exists");
        itemRepo.addItem(item);
    }

    @Override
    public List<Item> searchItem(ItemFilter itemFilter) {
        Comparator<Item> comparator;

        if (itemFilter.getOrderBy() != null) {
            comparator = (itemFilter.getOrder() == Order.DESC)
                    ? Comparator.comparing(itemFilter.getOrderBy()).reversed()
                    : Comparator.comparing(itemFilter.getOrderBy());
        } else {
            comparator = Comparator.comparing(Item::getPrice); // default sorting
        }

        return itemRepo.getItems().stream()
                .filter(item -> itemFilter.getPredicates().stream().allMatch(p -> p.test(item)))
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
