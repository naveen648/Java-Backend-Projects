package com.example.librarymanagement.Controller;


import com.example.librarymanagement.DataAcessLayer.IssuedBooks;
import com.example.librarymanagement.DataAcessLayer.IssuedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@RestController
public class IssuedBooksResource {
    private final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    IssuedBooksRepository issuedBooksRepository;

    @GetMapping(value = "issuedBooks")
    List<IssuedBooks> findAll()
    {
        return issuedBooksRepository.findAll();
    }

    @PostMapping(value = "issueBook")
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBooks issueBook(@RequestBody IssuedBooks issuedBooks) throws Exception {
        try{
            return issuedBooksRepository.save(issuedBooks);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new Exception();
        }
    }

    @GetMapping(value = "searchIssuedBooksByUser")
    public List<IssuedBooks> searchIssuedBooksByUser(@RequestParam (value="q")String userId)
    {
        LOGGER.info("searchIssuedBooksByUser called with userId "+userId);
        List<IssuedBooks> books=new ArrayList<IssuedBooks>();
        ArrayList<IssuedBooks> list=new ArrayList<IssuedBooks>();

        int id=Integer.parseInt(userId);
        for(IssuedBooks book:books )
            if(book.getUser_id()==id)
                list.add(book);
        return list;
    }

}
