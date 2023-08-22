package com.example.librarymanagement.DataAcessLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBooksRepository extends JpaRepository<IssuedBooks,Integer> {
}
