package by.itechart.mongo_documentation.atlas.manualy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

  private String type;

  private Double score;
}
