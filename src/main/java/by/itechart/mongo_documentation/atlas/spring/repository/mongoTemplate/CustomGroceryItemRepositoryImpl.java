package by.itechart.mongo_documentation.atlas.spring.repository.mongoTemplate;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomGroceryItemRepositoryImpl implements CustomGroceryItemRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public GroceryItem saveItem(GroceryItem item) {

    log.info("Save a grocery item");

    return mongoTemplate.save(item);
  }

  @Override
  public GroceryItem updateItem(String name, GroceryItem item) throws GroceryItemNotFoundException {

    log.info("Update a grocery item with the name = {}", name);

    Query query = new Query(Criteria.where("name").is(name));

    Update update = new Update();
    update.set("name", item.getName());
    update.set("category", item.getCategory());
    update.set("quantity", item.getQuantity());

    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, GroceryItem.class);

    log.info("Updated items count: {}", updateResult.getModifiedCount());

    query = new Query(Criteria.where("name").is(item.getName()));

    return mongoTemplate.findOne(query, GroceryItem.class);
  }

  @Override
  public Optional<GroceryItem> getItemByName(String name) {

    log.info("Get a grocery item with the name = {}", name);

    Query query = new Query(Criteria.where("name").is(name));

    GroceryItem item = mongoTemplate.findOne(query, GroceryItem.class);

    if( Objects.isNull(item)) {
      return Optional.ofNullable(null);
    }

    return Optional.of(item);
  }

  @Override
  public void deleteGroceryItem(String name) {

    log.info("Delete a grocery item with the name = {}", name);

    Query query = new Query(Criteria.where("name").is(name));

    GroceryItem item = mongoTemplate.findOne(query, GroceryItem.class);
    DeleteResult remove = mongoTemplate.remove(item);

    log.info("Number of items that was removed: {}", remove.getDeletedCount());
  }

  @Override
  public List<GroceryItem> findItemsByCategory(String category) {

    Query query = new Query(Criteria.where("category").is(category));

    return mongoTemplate.find(query, GroceryItem.class);
  }
}
