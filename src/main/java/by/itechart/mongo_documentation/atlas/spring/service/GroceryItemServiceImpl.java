package by.itechart.mongo_documentation.atlas.spring.service;

import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import by.itechart.mongo_documentation.atlas.spring.repository.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class GroceryItemServiceImpl implements GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;

    @Override
    public GroceryItem saveItem(GroceryItem groceryItem) {

        log.info("Store a new grocery item");

        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public GroceryItem getItemByName(String name) throws GroceryItemNotFoundException {

        log.info("Fetch a grocery item by name = {}", name);

        return groceryItemRepository.getGroceryItemByName(name)
                .orElseThrow(() -> new GroceryItemNotFoundException(
                        String.format("A groceryItem with the name = %s is not found", name)));
    }

    @Override
    public List<GroceryItem> findItemsByCategory(String category) {

        log.info("Fetch a collection of grocery items by the category = {}", category);

        return groceryItemRepository.findAllByCategory(category);
    }
}
