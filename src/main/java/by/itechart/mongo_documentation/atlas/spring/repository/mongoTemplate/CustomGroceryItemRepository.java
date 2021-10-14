package by.itechart.mongo_documentation.atlas.spring.repository.mongoTemplate;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;

import java.util.List;
import java.util.Optional;

public interface CustomGroceryItemRepository {

    GroceryItem saveItem(GroceryItem item);

    GroceryItem updateItem(String name, GroceryItem item);

    Optional<GroceryItem> getItemByName(String name);

    void deleteGroceryItem(String name);

    List<GroceryItem> findItemsByCategory(String category);

    GroceryItem updateItemsQuantityByNameAndCategory(String name, String category, Integer quantity);
}
