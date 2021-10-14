package by.itechart.mongo_documentation.atlas.spring.service.mongoTemplate;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;

import java.util.List;

public interface CustomGroceryItemService {

  GroceryItem saveItem(GroceryItem item);

  GroceryItem updateItem(String name, GroceryItem item) throws GroceryItemNotFoundException;

  GroceryItem getItemByName(String name) throws GroceryItemNotFoundException;

  void deleteGroceryItem(String name);

  List<GroceryItem> findItemsByCategory(String category);

  GroceryItem updateItemsQuantityByNameAndCategory(String name, String category, Integer quantity);
}
