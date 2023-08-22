package com.example.librarymanagement.Controller;


import com.example.librarymanagement.DataAcessLayer.Book;
import com.example.librarymanagement.DataAcessLayer.BookRepository;
import com.example.librarymanagement.DataAcessLayer.IssuedBooksRepository;
import com.example.librarymanagement.DataAcessLayer.UserRepository;
import com.example.librarymanagement.Exception.BookNotFoundException;
import com.example.librarymanagement.Util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class BookResource {
    private final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    IssuedBooksRepository rentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BookRepository repository;

    BookValidator validator=new BookValidator();

    @GetMapping("/books")
    List<Book> findall(){
        LOGGER.info("findAll Called");
        List<Book> list=new ArrayList<Book>();

        try{
            list=repository.findAll();
            if(list.size()==0) {
                LOGGER.severe("No Book Found");
                throw new BookNotFoundException(5);
            }
        }
        catch(BookNotFoundException exc)
        {
            LOGGER.severe(exc.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Book Found",exc);
        }
        return list;
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    Book coolBook(@RequestBody Book coolBook){
        if(validator.isValid(coolBook))
            return repository.save(coolBook);
        else
            LOGGER.severe("coolBook Not Found");
            return null;
    }

    @GetMapping("/searchBooksByAuthor")
    public List<Book> searchBooksByAuthor(@RequestParam(value = "q") String author){
        List<Book> books=bookRepository.findAll();
        ArrayList<Book> list=new ArrayList<Book>();

        for(Book book:books){
            if(book.getAuthor().equals(author))
                list.add(book);
        }
        return list;
    }

    @GetMapping("/searchBooksBySubject")
    public List<Book> searchBooksBySubject(@RequestParam(value = "q") String subject)
    {
        List<Book> books=bookRepository.findAll();
        ArrayList<Book> list=new ArrayList<Book>();

        for(Book book:books)
            if(book.getSubject().equals(subject))
                list.add(book);
        return list;
    }


}
