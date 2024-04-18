package com.hendisantika.springbootrestapipostgresql.controller;

import com.hendisantika.springbootrestapipostgresql.entity.Author;
import com.hendisantika.springbootrestapipostgresql.repository.AuthorRepository;

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
@RequestMapping("/api/authors")
public class AuthorRestController {

    Logger logger = LogManager.getLogger(AuthorRestController.class);

    @Autowired
    private AuthorRepository repository;

    @PostMapping
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        logger.debug("addAuthor({}) called", author.getFirstName() + author.getLastName());
        logger.info("Adding author {}", author);
        logger.info("Author added successfully");
        logger.info("I am a Post Request and I am done !");
        return new ResponseEntity<>(repository.save(author), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Author>> getAllAuthors() {
        logger.info("Retrieving all authors");
        logger.info("I am a Get Request and I am done !");
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        logger.info("Retrieving author with ID: {}", id);
        logger.info("I am a Get Request and I am done !");
        return new ResponseEntity<Author>(repository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping(params = {"firstName"})
    public ResponseEntity<Collection<Author>> findAuthorsByFirstName(@RequestParam(value = "firstName") String firstName) {
        logger.info("Finding authors with first name: {}", firstName);
        logger.info("I am a Get Request and I am done !");
        return new ResponseEntity<>(repository.findByFirstName(firstName), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author author) {

        Optional<Author> currentAuthorOpt = repository.findById(id);

        if (!currentAuthorOpt.isPresent()) {
            logger.warn("Author with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }

        Author currentAuthor = currentAuthorOpt.get();
        currentAuthor.setFirstName(author.getFirstName());
        currentAuthor.setLastName(author.getLastName());
        currentAuthor.setBirthYear(author.getBirthYear());
        currentAuthor.setBiography(author.getBiography());
        currentAuthor.setEmail(author.getEmail());
        currentAuthor.setIsbns(author.getIsbns());

        logger.info("Updating author with ID: {}", id);
        logger.info("I am a Put Request and I am done !");
        return new ResponseEntity<>(repository.save(currentAuthor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable Long id) {
        repository.deleteById(id);
        logger.info("Deleted author with ID: {}", id);
        logger.info("I am a Delete Request and I am done !");
    }

    @DeleteMapping
    public void deleteAllAuthors() {
        repository.deleteAll();
        logger.info("Deleted all authors");
        logger.info("I am a Delete Request and I am done !");
    }
}
