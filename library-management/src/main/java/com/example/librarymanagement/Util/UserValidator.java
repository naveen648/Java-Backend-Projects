package com.example.librarymanagement.Util;


import com.example.librarymanagement.DataAcessLayer.User;

public class UserValidator {
    public static boolean isValid(User user){
        if(user.getEmail()==""||user.getEmail()==null)
            return false;
        return true;
    }
}