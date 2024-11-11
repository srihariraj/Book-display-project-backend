package com.springboot.jpaapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.jpaapp.exception.ResourceNotFoundException;
import com.springboot.jpaapp.model.Book;
import com.springboot.jpaapp.repository.BookRepository;
import com.springboot.jpaapp.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
	}

	@Override
	public Book updateBook(Long id, Book bookDetails) {
		Book book = getBookById(id);
		book.setTitle(bookDetails.getTitle());
		book.setDescription(bookDetails.getDescription());
		book.setPublished(bookDetails.isPublished());
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		if (!bookRepository.existsById(id)) {
			throw new ResourceNotFoundException("Book not found with id " + id);
		}
		bookRepository.deleteById(id);
	}

	@Override
	public void deleteAllBooks() {
		bookRepository.deleteAll();
	}

	@Override
	public List<Book> findPublishedBooks() {
		return bookRepository.findByPublished(true);
	}

	@Override
	public List<Book> findBooksByTitle(String keyword) {
		return bookRepository.findByTitleContaining(keyword);
	}
}
