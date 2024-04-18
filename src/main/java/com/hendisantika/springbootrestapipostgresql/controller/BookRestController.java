package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.entity.Book;
import com.hendisantika.springbootrestapipostgresql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    Logger logger = LogManager.getLogger(BookRestController.class);

    @Autowired
    private BookRepository repository;

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        logger.debug("addBook({}) called", book.getTitle());
        logger.info("Adding book {}", book);
        logger.info("Book added successfully");
        logger.info("I am a Post Request and I am done !");
        return new ResponseEntity<>(repository.save(book), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Book>> getAllBooks() {
        logger.info("Retrieving all books");
        logger.info("I am a Get Request and I am done !");
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        logger.info("Retrieving book with ID: {}", id);
        logger.info("I am a Get Request and I am done !");
        return new ResponseEntity<Book>(repository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping(params = {"title"})
    public ResponseEntity<Collection<Book>> findBookByTitle(@RequestParam(value = "title") String title) {
        logger.info("Finding books with title: {}", title);
        logger.info("I am a Get Request and I am done !");
        return new ResponseEntity<>(repository.findByTitle(title), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {

        Optional<Book> currentBookOpt = repository.findById(id);

        if (!currentBookOpt.isPresent()) {
            logger.warn("Book with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }

        Book currentBook = currentBookOpt.get();
        currentBook.setName(book.getTitle());
        currentBook.setDescription(book.getDescription());
        currentBook.setTags(book.getTags());

        logger.info("Updating book with ID: {}", id);
        logger.info("I am a Put Request and I am done !");
        return new ResponseEntity<>(repository.save(currentBook), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        repository.deleteById(id);
        logger.info("Deleted book with ID: {}", id);
        logger.info("I am a Delete Request and I am done !");
    }

    @DeleteMapping
    public void deleteAllBooks() {
        repository.deleteAll();
        logger.info("Deleted all books");
        logger.info("I am a Delete Request and I am done !");
    }
}