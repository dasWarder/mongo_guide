package by.itechart.mongo_documentation.atlas.spring.map;

import by.itechart.mongo_documentation.atlas.spring.dto.GroceryItemDetailsResponse;
import by.itechart.mongo_documentation.atlas.spring.dto.StoreGroceryItemRequest;
import by.itechart.mongo_documentation.atlas.spring.model.GroceryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GroceryItemMapper {

    public GroceryItem storeGroceryItemRequestToGroceryItem(StoreGroceryItemRequest request) {

        log.info("Map a groceryItem store object to a groceryItem object");

        GroceryItem groceryItem = GroceryItem.builder()
                .name(request.getName())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .build();

        return groceryItem;
    }

    public GroceryItemDetailsResponse groceryItemToGroceryItemDetailsResponse(GroceryItem groceryItem) {

        log.info("Map a groceryItem object to a groceryItem details object");

        GroceryItemDetailsResponse response = GroceryItemDetailsResponse.builder()
                .id(groceryItem.getId())
                .name(groceryItem.getName())
                .quantity(groceryItem.getQuantity())
                .category(groceryItem.getCategory())
                .build();

        return response;
    }
}
