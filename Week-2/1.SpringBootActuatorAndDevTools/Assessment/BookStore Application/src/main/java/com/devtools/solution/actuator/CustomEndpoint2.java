package com.devtools.solution.actuator;

import com.devtools.solution.entity.Book;
import com.devtools.solution.repo.BookDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Endpoint(id = "stock")
@Component
public class CustomEndpoint2 {
    @Autowired
    BookDal bookDal;

    Map< String, Integer > quantitiesMap = new HashMap<>();

    @ReadOperation
    public Map<String, Integer> getBookQuantities(){
        List<Book> bookList = bookDal.findAll();
        for(Book b : bookList) {
            quantitiesMap.put(b.getTitle(), b.getQuantity());
        }
        return quantitiesMap;
    }
}
