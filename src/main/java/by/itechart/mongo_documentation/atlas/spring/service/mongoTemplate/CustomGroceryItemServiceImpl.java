package by.itechart.mongo_documentation.atlas.spring.service.mongoTemplate;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import by.itechart.mongo_documentation.atlas.spring.repository.mongoTemplate.CustomGroceryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomGroceryItemServiceImpl implements CustomGroceryItemService {

  private final CustomGroceryItemRepository groceryItemRepository;

  @Override
  @Transactional
  public GroceryItem saveItem(GroceryItem item) {

    log.info("Check correctness of the input object before storing it");
    Assert.notNull(item, "The item must be not null");

    return groceryItemRepository.saveItem(item);
  }

  @Override
  @Transactional
  public GroceryItem updateItem(String name, GroceryItem item) throws GroceryItemNotFoundException {

    log.info(
        "Check correctness of the input object and the param name = {} before updating it", name);
    Assert.notNull(name, "The name must be not null");
    Assert.notNull(item, "The item must be not null");

    return groceryItemRepository.updateItem(name, item);
  }

  @Override
  @Transactional(readOnly = true)
  public GroceryItem getItemByName(String name) throws GroceryItemNotFoundException {

    log.info("Check correctness of the param before fetching an object with the name = {}", name);
    Assert.notNull(name, "The name must be not null");

    return groceryItemRepository
        .getItemByName(name)
        .orElseThrow(
            () ->
                new GroceryItemNotFoundException(
                    String.format("A grocery item with the name = %s not found", name)));
  }

  @Override
  @Transactional
  public void deleteGroceryItem(String name) {

    log.info("Check correctness of the param name = {} before deleting an object", name);
    Assert.notNull(name, "The name must be not null");

    groceryItemRepository.deleteGroceryItem(name);
  }

  @Override
  @Transactional(readOnly = true)
  public List<GroceryItem> findItemsByCategory(String category) {

    log.info(
        "Check correctness of the param category = {} before receiving a collection of all objects from this category");
    Assert.notNull(category, "The category must be not null");

    return groceryItemRepository.findItemsByCategory(category);
  }
}
