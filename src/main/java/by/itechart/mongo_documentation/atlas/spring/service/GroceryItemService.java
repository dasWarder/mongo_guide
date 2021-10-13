package by.itechart.mongo_documentation.atlas.spring.service;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;

import java.util.List;

public interface GroceryItemService {

    GroceryItem saveItem(GroceryItem groceryItem);

    GroceryItem getItemByName(String name) throws GroceryItemNotFoundException;

    List<GroceryItem> findItemsByCategory(String category);
}
