package by.itechart.mongo_documentation.atlas.manualy;

import by.itechart.mongo_documentation.atlas.manualy.model.Grade;
import by.itechart.mongo_documentation.atlas.manualy.model.Score;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Collections.singletonList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


public class Mapping {

    private static final String dbUri = "mongodb+srv://m220student:m220password@mflix.mhcbz.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

    public static void main(String[] args) {

        ConnectionString connectionString = new ConnectionString(dbUri);
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase("sample_training");
            MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);

            Grade grade = new Grade();
            grade.setStudentId(1d);
            grade.setClassId(10d);
            grade.setScores(singletonList(new Score("homework", 50d)));

            grades.insertOne(grade);

            Grade receivedGrade = grades.find(eq("student_id", 1d)).first();
            System.out.println("RECEIVED GRADE: " + receivedGrade);

        }
    }
}
