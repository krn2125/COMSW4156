package dev.coms4156.project.individualproject.controller;

import dev.coms4156.project.individualproject.model.Book;
import dev.coms4156.project.individualproject.service.MockApiService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller that handles routes of the individual.
 */
@RestController
public class RouteController {

  private final MockApiService mockApiService;
  private final Logger logger = Logger.getLogger(RouteController.class.getName());

  /**
   * Constructor.
   *
   * @param mockApiService
   *     This function is a constructor.
   */
  public RouteController(final MockApiService mockApiService) {
    this.mockApiService = mockApiService;
  }

  @GetMapping({"/", "/index"})
  public String index() {
    return "Welcome to the home page! In order to make an API call direct your browser"
        + "or Postman to an endpoint.";
  }

  /**
   * Returns the details of the specified book.
   *
   * @param id An {@code int} representing the unique identifier of the book to retrieve.
   *
   * @return A {@code ResponseEntity} containing either the matching {@code Book} object with an
   *         HTTP 200 response, or a message indicating that the book was not
   *         found with an HTTP 404 response.
   */
  @GetMapping({"/book/{id}"})
  public ResponseEntity<?> getBook(@PathVariable final int id) {
    List<Book> books = mockApiService.getBooks();
    if (books ==  null) {
      return new ResponseEntity<>("Book service unavailable", HttpStatus.NOT_FOUND);
    }
    for (final Book book : mockApiService.getBooks()) {
      if (book.getId() == id) {
        return new ResponseEntity<>(book, HttpStatus.OK);
      }
    }

    return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
  }

  /**
   * Get and return a list of all the books with available copies.
   *
   * @return A {@code ResponseEntity} containing a list of available {@code Book} objects with an
   *         HTTP 200 response if sucessful, or a message indicating an error occurred with an
   *         HTTP 500 response.
   */
  @PutMapping({"/books/available"})
  public ResponseEntity<?> getAvailableBooks() {
    List<Book> books = mockApiService.getBooks();
    if (books ==  null) {
      return new ResponseEntity<>("Book service unavailable", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    try {
      final List<Book> availableBooks = new ArrayList<>();

      for (final Book book : mockApiService.getBooks()) {
        if (book.hasCopies()) {
          availableBooks.add(book);
        }
      }

      return new ResponseEntity<>(availableBooks, HttpStatus.OK);
    } catch (Exception e) {
      if (logger.isLoggable(Level.SEVERE)) {
        logger.severe("Unexpected error:" + e.getMessage());
      }
      return new ResponseEntity<>("Error occurred when getting all available books",
              HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Adds a copy to the {@code} Book object if it exists.
   *
   * @param bookId An {@code Integer} representing the unique id of the book.
   * @return A {@code ResponseEntity} containing the updated {@code Book} object with an
   *         HTTP 200 response if successful or HTTP 404 if the book is not found,
   *         or a message indicating an error occurred with an HTTP 500 code.
   */
  @PatchMapping({"/book/{bookId}/add"})
  public ResponseEntity<?> addCopy(@PathVariable final Integer bookId) {
    List<Book> books = mockApiService.getBooks();
    if (books ==  null) {
      return new ResponseEntity<>("Book service unavailable", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    try {
      for (final Book book : mockApiService.getBooks()) {
        if (bookId.equals(book.getId())) {
          book.addCopy();
          return new ResponseEntity<>(book, HttpStatus.OK);
        }
      }

      return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>("Book not found.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
