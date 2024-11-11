package com.springboot.jpaapp.service;

import java.util.List;

import com.springboot.jpaapp.model.Book;

public interface BookService {
    Book createBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    void deleteAllBooks();
    List<Book> findPublishedBooks();
    List<Book> findBooksByTitle(String keyword);
}
