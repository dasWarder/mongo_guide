package by.itechart.mongo_documentation.atlas.spring.exception;

public class GroceryItemNotFoundException extends Throwable {

  public GroceryItemNotFoundException() {}

  public GroceryItemNotFoundException(String message) {
    super(message);
  }
}
