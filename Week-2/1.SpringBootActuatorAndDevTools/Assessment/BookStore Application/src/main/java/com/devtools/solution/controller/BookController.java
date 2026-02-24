package com.devtools.solution.controller;

import com.devtools.solution.DTO.BookDto;
import com.devtools.solution.entity.Book;
import com.devtools.solution.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return bookService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody BookDto bookDto) {
        bookService.save(bookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.bookList();
    }
}
