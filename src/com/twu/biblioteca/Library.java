package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Library {
    private ArrayList bookList;
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    public Library(ArrayList bookList, PrintStream printStream, BufferedReader bufferedReader) {
        this.bookList = bookList;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    public addBook(Book book){
        bookList.add(book);
    }

    public void listBooks(){
        for(int i = 0; i < bookList.size(); i++){
            bookList.printDetails();
        }
    }
}
