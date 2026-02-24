package com.devtools.solution.service;

import com.devtools.solution.DTO.BookDto;
import com.devtools.solution.entity.Book;
import com.devtools.solution.repo.BookDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDal bookDal;

    public Book getById(int id) {
        return bookDal.findById(id).get();
    }

    public void save(BookDto bookDto) {
        Book book = new Book();

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());

        bookDal.save(book);
    }

    public void delete(int id) {
        bookDal.deleteById(id);
    }

    public List<Book> bookList() {
        return bookDal.findAll();
    }
}
