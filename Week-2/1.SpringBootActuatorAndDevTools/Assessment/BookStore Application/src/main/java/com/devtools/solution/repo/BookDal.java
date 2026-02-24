package com.devtools.solution.repo;

import com.devtools.solution.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDal extends JpaRepository<Book, Integer> {
}
