package models;

import models.enums.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ItemFilter {
    private final List<String> brandList;
    private final List<String> categoryList;
    private Function<Item, Comparable> orderBy;
    private final Order order;
    private final Integer priceFrom;
    private final Integer priceTo;

    private ItemFilter(Builder builder) {
        this.brandList = builder.brand;
        this.categoryList = builder.category;
        this.orderBy = builder.orderBy;
        this.order = builder.order;
        this.priceFrom = builder.priceFrom;
        this.priceTo = builder.priceTo;
    }

    public static class Builder {
        private List<String> brand;
        private List<String> category;
        private Function<Item, Comparable > orderBy;
        private Order order;
        private Integer priceFrom;
        private Integer priceTo;

        public Builder brandList(List<String> brand) {
            this.brand = brand;
            return this;
        }

        public Builder categoryList(List<String> category) {
            this.category = category;
            return this;
        }

        public Builder orderBy(Function<Item, Comparable> orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder priceFrom(Integer priceFrom) {
            this.priceFrom = priceFrom;
            return this;
        }

        public Builder priceTo(Integer priceTo) {
            this.priceTo = priceTo;
            return this;
        }

        public ItemFilter build() {
            return new ItemFilter(this);
        }


    }

    public Function<Item, Comparable> getOrderBy() {
        return orderBy;
    }
    public Order getOrder() { return order; }

    @Override
    public String toString() {
        return "ItemFilter{" +
                "brand='" + brandList + '\'' +
                ", category='" + categoryList + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", order=" + order +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                '}';
    }

    public List<Predicate<Item>> getPredicates() {
        List<Predicate<Item>> predicates = new ArrayList<>();

        if (brandList != null && !brandList.isEmpty()) {
            predicates.add(item -> brandList.stream().anyMatch(b -> b.equalsIgnoreCase(item.getBrand())));
        }
        if (categoryList != null && !categoryList.isEmpty()) {
            predicates.add(item -> categoryList.stream().anyMatch(b -> b.equalsIgnoreCase(item.getCategory())));
        }
        if (priceFrom != null) {
            predicates.add(item -> item.getPrice() >= priceFrom);
        }
        if (priceTo != null) {
            predicates.add(item -> item.getPrice() <= priceTo);
        }

        // we can add new filter predicates here

        return predicates;
    }
    public static Function<Item, Comparable> getOrderByFunction(String orderByField) {
        switch (orderByField.toLowerCase()) {
            case "price": return Item::getPrice;
            case "quantity": return Item::getQuantity;
            case "id": return Item::getId;
            // we can add new orderByFields here
            default: return null;
        }
    }
}

