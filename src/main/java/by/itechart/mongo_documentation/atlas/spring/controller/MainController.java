package by.itechart.mongo_documentation.atlas.spring.controller;

import by.itechart.mongo_documentation.atlas.spring.dto.GroceryItemDetailsResponse;
import by.itechart.mongo_documentation.atlas.spring.dto.StoreGroceryItemRequest;
import by.itechart.mongo_documentation.atlas.spring.exception.GroceryItemNotFoundException;
import by.itechart.mongo_documentation.atlas.spring.map.GroceryItemMapper;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import by.itechart.mongo_documentation.atlas.spring.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class MainController {

    private final GroceryItemMapper mapper;

    private final GroceryItemService groceryItemService;

    @PostMapping
    public ResponseEntity<GroceryItemDetailsResponse> saveItem(@RequestBody StoreGroceryItemRequest request) {

        GroceryItem groceryItem = mapper.storeGroceryItemRequestToGroceryItem(request);
        GroceryItem stored = groceryItemService.saveItem(groceryItem);
        GroceryItemDetailsResponse response = mapper.groceryItemToGroceryItemDetailsResponse(stored);

        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/items")).body(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GroceryItemDetailsResponse> getItemByName(@PathVariable("name") String name) throws GroceryItemNotFoundException {

        GroceryItem itemByName = groceryItemService.getItemByName(name);
        GroceryItemDetailsResponse fetched = mapper.groceryItemToGroceryItemDetailsResponse(itemByName);

        return ResponseEntity.ok(fetched);
    }

    @GetMapping
    public ResponseEntity<List<GroceryItemDetailsResponse>> getItemsByCategory(@RequestParam("category") String category) {

        List<GroceryItemDetailsResponse> items = groceryItemService.findItemsByCategory(category)
                .stream()
                .map(mapper::groceryItemToGroceryItemDetailsResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(items);
    }
}
