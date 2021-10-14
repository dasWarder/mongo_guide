package by.itechart.mongo_documentation.atlas.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItemDetailsResponse {

  private String id;

  private String name;

  private Integer quantity;

  private String category;
}
