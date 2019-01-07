package com.example.Book.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.Book.Entities.Book;
import com.example.Book.Repositories.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepo bookRespository;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRespository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return bookRespository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRespository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRespository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book blogToUpdate = bookRespository.findById(id).get();
        blogToUpdate.setTitle(book.getTitle());
        blogToUpdate.setAuthor(book.getAuthor());
        blogToUpdate.setDescription(book.getDescription());

        return bookRespository.save(blogToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        bookRespository.deleteById(id);
        return true;
    }
}