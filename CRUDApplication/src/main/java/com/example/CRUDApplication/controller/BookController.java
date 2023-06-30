package com.example.CRUDApplication.controller;

import com.example.CRUDApplication.exception.ErrorResponse;
import com.example.CRUDApplication.model.Book;
import com.example.CRUDApplication.exception.NoSuchBookExistsException;
import com.example.CRUDApplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        try{
            List<Book> bookList = new ArrayList<>();
            bookRepository.findAll().forEach(bookList::add);
            //bookList.addAll(bookRepository.findAll());


            if(bookList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList,HttpStatus.OK);

        }catch (Exception ex){
            //System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getBooks/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){

        try{
            //List<Book> bookList = new ArrayList<>();
            Optional<Book> bookData = bookRepository.findById(id);
            //Book bookData = bookRepository.findById(id);


            if(bookData.isPresent())
                return new ResponseEntity<>(bookData.get(), HttpStatus.OK);

                //return new ResponseEntity<>(HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //return null;

        }catch(Exception ex){
            //System.out.println(ex);
            //return null;

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book bookObj = bookRepository.save(book);

        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }

    @PostMapping("/addBook/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData){
        Optional<Book> oldBookData = bookRepository.findById(id);
        if(oldBookData.isPresent()){
            Book updatedbookData = oldBookData.get();
            updatedbookData.setTitle(newBookData.getTitle());
            updatedbookData.setAuthor(newBookData.getAuthor());

            Book bookObj = bookRepository.save(updatedbookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id){

        Optional<Book> bookData = bookRepository.findById(id);
        if(bookData.isEmpty())
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            throw new NoSuchBookExistsException("No Book Exists");

        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(value = NoSuchBookExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchBookExistsException(NoSuchBookExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
