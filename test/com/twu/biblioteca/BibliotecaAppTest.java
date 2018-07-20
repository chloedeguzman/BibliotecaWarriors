package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {

    private PrintStream printStream;
    private Scanner scanner;
    private BibliotecaApp app;

    @Before
    public void setUp(){
        app = new BibliotecaApp();
        printStream = new PrintStream(System.out);
        System.setOut(printStream);
    }

    @Test(expected = NoSuchElementException.class)
    public void listBooksTest() {
        ByteArrayInputStream input = new ByteArrayInputStream("list books".getBytes());
        System.setIn(input);

        Library library = mock (Library.class);
        app.main(null);
        verify(library, times(1)).listBooks();

    }

    @Test(expected = NoSuchElementException.class)
    public void checkoutTest() {
        ByteArrayInputStream input = new ByteArrayInputStream("checkout".getBytes());
        System.setIn(input);

        Library library = mock (Library.class);
        app.main(null);
//        verify(library, times(1)).();

    }

    @Test(expected = NoSuchElementException.class)
    public void returnTest() {
        ByteArrayInputStream input = new ByteArrayInputStream("return".getBytes());
        System.setIn(input);

        Library library = mock (Library.class);
        app.main(null);
//        verify(library, times(1)).();

    }

    @Test(expected = NoSuchElementException.class)
    public void invalidMenuOptions(){
        ByteArrayInputStream input = new ByteArrayInputStream("INVALID".getBytes());
        System.setIn(input);

        app.main(null);
        verify(printStream).println("Invalid Menu Option");
    }

    @Test
    public void quitTest(){
        ByteArrayInputStream input2 = new ByteArrayInputStream("quit".getBytes());
        System.setIn(input2);

        app.main(null);
        assertFalse(app.isRunning);
    }



}