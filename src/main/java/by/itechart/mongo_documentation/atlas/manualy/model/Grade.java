package by.itechart.mongo_documentation.atlas.manualy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

  private ObjectId id;

  @BsonProperty(value = "class_id")
  private Double classId;

  private List<Score> scores;

  @BsonProperty(value = "student_id")
  private Double studentId;
}
