package com.example.librarymanagement.Util;


import com.example.librarymanagement.DataAcessLayer.Book;

public class BookValidator {
    public boolean isValid(Book book){
        if(book.getTitle()==""||book.getTitle()==null)
            return false;
        return true;
    }
}
