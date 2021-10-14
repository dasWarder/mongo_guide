package by.itechart.mongo_documentation.atlas.spring.repository;

import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

  Optional<GroceryItem> getGroceryItemByName(String name);

  @Query(fields = "{ 'name': 1, 'quantity': 1 }")
  List<GroceryItem> findAllByCategory(String category);

  long count();

  void deleteByName(String name);
}
