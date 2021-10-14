package by.itechart.mongo_documentation.atlas.spring.service.mongoRepo;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import by.itechart.mongo_documentation.atlas.spring.repository.mongoRepo.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroceryItemServiceImpl implements GroceryItemService {

  private final GroceryItemRepository groceryItemRepository;

  @Override
  @Transactional
  public GroceryItem saveItem(GroceryItem groceryItem) {

    log.info("Store a new grocery item");

    return groceryItemRepository.save(groceryItem);
  }

  @Override
  @Transactional
  public List<GroceryItem> updateCategory(String oldCategory, String category) {

    log.info("Update all groceryItems by category");

    List<GroceryItem> items = groceryItemRepository.findAllByCategory(oldCategory);
    items.stream().forEach(i -> i.setCategory(category));

    return groceryItemRepository.saveAll(items);
  }

  @Override
  @Transactional(readOnly = true)
  public GroceryItem getItemByName(String name) throws GroceryItemNotFoundException {

    log.info("Fetch a grocery item by the name = {}", name);

    return groceryItemRepository
        .getGroceryItemByName(name)
        .orElseThrow(
            () ->
                new GroceryItemNotFoundException(
                    String.format("A groceryItem with the name = %s is not found", name)));
  }

  @Override
  @Transactional(readOnly = true)
  public List<GroceryItem> findItemsByCategory(String category) {

    log.info("Fetch a collection of grocery items by the category = {}", category);

    return groceryItemRepository.findAllByCategory(category);
  }

  @Override
  @Transactional
  public void deleteItemByName(String name) {

    log.info("Delete a grocery item by the name = {}", name);

    groceryItemRepository.deleteByName(name);
  }

  @Override
  @Transactional(readOnly = true)
  public Long count() {

    log.info("Fetch count");

    return groceryItemRepository.count();
  }
}
