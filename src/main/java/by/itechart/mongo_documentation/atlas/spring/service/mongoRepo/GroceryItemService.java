package by.itechart.mongo_documentation.atlas.spring.service.mongoRepo;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;

import java.util.List;

public interface GroceryItemService {

  GroceryItem saveItem(GroceryItem groceryItem);

  List<GroceryItem> updateCategory(String oldCategory, String category);

  GroceryItem getItemByName(String name) throws GroceryItemNotFoundException;

  List<GroceryItem> findItemsByCategory(String category);

  void deleteItemByName(String name);

  Long count();
}
