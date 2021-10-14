package by.itechart.mongo_documentation.atlas.spring.controller.mongoTemplate;

import by.itechart.mongo_documentation.atlas.spring.dto.GroceryItemDetailsResponse;
import by.itechart.mongo_documentation.atlas.spring.dto.StoreGroceryItemRequest;
import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.map.GroceryItemMapper;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import by.itechart.mongo_documentation.atlas.spring.service.mongoTemplate.CustomGroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/temp/items")
public class MongoTemplateController {

  private final GroceryItemMapper mapper;

  private final CustomGroceryItemService groceryItemService;

  @PostMapping
  public ResponseEntity<GroceryItemDetailsResponse> saveItem(
      @RequestBody StoreGroceryItemRequest request) {

    GroceryItem groceryItem = mapper.storeGroceryItemRequestToGroceryItem(request);
    GroceryItem stored = groceryItemService.saveItem(groceryItem);
    GroceryItemDetailsResponse response = mapper.groceryItemToGroceryItemDetailsResponse(stored);

    return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/temp/items"))
        .body(response);
  }

  @PutMapping
  public ResponseEntity<GroceryItemDetailsResponse> updateItemByName(
      @RequestParam("name") String name, @RequestBody StoreGroceryItemRequest request)
      throws GroceryItemNotFoundException {

    GroceryItem item = mapper.storeGroceryItemRequestToGroceryItem(request);
    GroceryItem updated = groceryItemService.updateItem(name, item);
    GroceryItemDetailsResponse response = mapper.groceryItemToGroceryItemDetailsResponse(updated);

    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<GroceryItemDetailsResponse> getItemByName(@RequestParam("name") String name)
      throws GroceryItemNotFoundException {

    GroceryItem itemByName = groceryItemService.getItemByName(name);
    GroceryItemDetailsResponse response =
        mapper.groceryItemToGroceryItemDetailsResponse(itemByName);

    return ResponseEntity.ok(response);
  }

  @GetMapping("/find")
  public ResponseEntity<List<GroceryItemDetailsResponse>> getItemsByCategory(
      @RequestParam("category") String category) {

    List<GroceryItemDetailsResponse> items =
        groceryItemService.findItemsByCategory(category).stream()
            .map(mapper::groceryItemToGroceryItemDetailsResponse)
            .collect(Collectors.toList());

    return ResponseEntity.ok(items);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteItemByName(@RequestParam("name") String name) {

    groceryItemService.deleteGroceryItem(name);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/update")
  public ResponseEntity<GroceryItemDetailsResponse> updateItemByNameAndCategory(
      @RequestParam("name") String name,
      @RequestParam("category") String category,
      @RequestParam("quantity") Integer quantity) {

    GroceryItem groceryItem = groceryItemService.updateItemsQuantityByNameAndCategory(name, category, quantity);
    GroceryItemDetailsResponse response = mapper.groceryItemToGroceryItemDetailsResponse(groceryItem);

    return ResponseEntity.ok(response);
  }
}
