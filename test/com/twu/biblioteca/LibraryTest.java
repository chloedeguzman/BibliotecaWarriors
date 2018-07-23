package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;



public class LibraryTest {
    private ArrayList<LibraryItem> testCatalog;
    private Library testLibrary;
    private User testUser;

    private Printer printer;
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    @Before
    public void setUp() {
        testCatalog = new ArrayList<LibraryItem>();
        testUser = new User("Joe Smith", "was@sad.xon", "123-231-2341", "1234");
        testUser.setLibraryID("123-1234");
        printStream = mock(PrintStream.class);
        printer = new Printer(printStream);
        bufferedReader = mock(BufferedReader.class);
        testLibrary = new Library(testCatalog, printStream, bufferedReader);
        testLibrary.addUser(testUser);
        System.setOut(printStream);
    }
    
    @Test
    public void shouldPrintNothingWhenThereAreNoItems() {
        testLibrary.listItems();
        verify(printStream).print("");
    }

    @Test
    public void shouldPrintBookTitleWhenThereIsOneItem() {
        Book testBook = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, true);

        testLibrary.addItem(testBook);
        testLibrary.listItems();
        verify(printStream).println(testBook.id + " | " + testBook.title + " | " + testBook.creator + " | " + testBook.pubDate);
    }


    @Test
    public void shouldPrintBothBookTitlesWhenThereAreTwoItems() throws IOException {
        Book bookTwo = new Book("B2", "Modern Romance", "Aziz Ansari", 2015, true);
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, true);

        testLibrary.addItem(bookOne);
        testLibrary.addItem(bookTwo);

        testLibrary.listItems();
        verify(printStream).println(bookOne.id + " | " + bookOne.title + " | " + bookOne.creator + " | " + bookOne.pubDate);
        verify(printStream).println(bookTwo.id + " | " + bookTwo.title + " | " + bookTwo.creator + " | " + bookTwo.pubDate);

    }

    @Test
    public void bookNotInLibrary(){
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, true);
        assertFalse(testLibrary.getCatalog().contains(bookOne));
    }

    @Test
    public void bookInLibrary(){
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, true);
        testLibrary.addItem(bookOne);
        assertTrue(testLibrary.getCatalog().contains(bookOne));
    }

    @Test
    public void correctBookCheckedOut() {
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, true);
        testLibrary.addItem(bookOne);
        testLibrary.checkoutById("B1");
        verify(printStream).println("Thank you! Enjoy the book.");
    }

    @Test
    public void unsuccessfulCheckoutByUnavailableBook() {
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, false);
        testLibrary.addItem(bookOne);
        testLibrary.checkoutById("B1");
        verify(printStream).println("That book is not available.");
    }

    @Test
    public void unsuccessfulCheckoutByDoesNotExist() {
        testLibrary.checkoutById("B10");
        verify(printStream).println("That book is not available.");
    }

    @Test
    public void correctBookReturned(){
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, false);
        testLibrary.addItem(bookOne);
        testLibrary.returnById("B1");
        verify(printStream).println("Thank you for returning the book.");

    }

    @Test
    public void unsuccessfulReturnByAvailableBook(){
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, true);
        testLibrary.addItem(bookOne);
        testLibrary.returnById("B1");
        verify(printStream).println("That is not a valid book to return.");
    }

    @Test
    public void unsuccessfulReturnByDoesNotExist(){
        testLibrary.returnById("B10");
        verify(printStream).println("That is not a valid book to return.");

    }

    @Test
    public void printOnlyAvailBooks() {
        Book bookOne = new Book("B1", "Flowers for Algernon", "Daniel Keyes", 1959, false);
        testLibrary.addItem(bookOne);
        Book bookTwo = new Book("B2", "Modern Romance", "Aziz Ansari", 2015, true);
        testLibrary.addItem(bookTwo);
        testLibrary.listItems();
        verify(printStream).println(bookTwo.id + " | " + bookTwo.title + " | " + bookTwo.creator + " | " + bookTwo.pubDate);
    }

    /****************** Movie Tests ********************/

//    @Test
//    public void shouldPrintNothingWhenThereAreNoMovies() {
//        testLibrary.listMovies();
//        verify(printStream).print("");
//    }
//
//    @Test
//    @Ignore
//    public void shouldPrintMovieTitleWhenThereIsOneMovie() {
//        Movie testMovie = new Movie("M1", "Hush", "Mr. Spook", 2008, "5", true);
//
//        testLibrary.addItem(testMovie);
//        testLibrary.listMovies();
//
//        verify(printStream).println(testMovie.title + " | " + testMovie.creator + " | " + testMovie.pubDate + " | " + testMovie.rating);
//    }
//
//
//    @Test
//    @Ignore
//    public void shouldPrintBothMovieTitlesWhenThereAreTwoMovies() throws IOException {
//        Movie movieTwo = new Movie("M2", "Total Recall", "Arnold Schwarzneggar", 2015, "3", true);
//        Movie movieOne = new Movie("M1", "Hush", "Mr. Spook", 2008, "5", true);
//
//        testLibrary.addItem(movieOne);
//        testLibrary.addItem(movieTwo);
//
//        testLibrary.listMovies();
//        verify(printStream).println(movieOne.title + " | " + movieOne.creator + " | " + movieOne.pubDate + " | " + movieOne.rating);
//        verify(printStream).println(movieTwo.title + " | " + movieTwo.creator + " | " + movieTwo.pubDate + " | " + movieTwo.rating);
//
//    }

    @Test
    public void correctMovieCheckedOut() {
        Movie movieOne = new Movie("M1", "Cindarella Story", "Hillary Duff", 2003, "10", true);
        testLibrary.addItem(movieOne);
        testLibrary.checkoutById("M1");
        verify(printStream).println("Thank you! Enjoy the movie.");
    }

    @Test
    public void unsuccessfulCheckoutByUnavailableMovie() {
        Movie movieOne = new Movie("M1", "Cindarella Story", "Hillary Duff", 2003, "10", false);
        testLibrary.addItem(movieOne);
        testLibrary.checkoutById("M1");
        verify(printStream).println("That movie is not available.");
    }


    @Test
    public void printOnlyAvailMovies() {
        Movie movieOne = new Movie("M1", "Cindarella Story", "Hillary Duff", 2003, "10", false);
        testLibrary.addItem(movieOne);
        Movie movieTwo = new Movie("M2", "Total Recall", "Arnold Schwarzneggar", 2015, "3", true);
        testLibrary.addItem(movieTwo);
        testLibrary.listItems();
        verify(printStream).println(movieTwo.id + " | " + movieTwo.title + " | " + movieTwo.creator + " | " + movieTwo.pubDate + " | " + movieTwo.rating);

    }

    @Test
    public void checkCurrentUser(){
        assertNull(testLibrary.getCurrentUser());
        testLibrary.setCurrentUser(testUser.getLibraryID());
        assertEquals(testUser.getLibraryID(), testLibrary.getCurrentUser());
    }

    @Test
    public void checkCredentialTest(){
        assertEquals(true, testLibrary.checkCredentials(testUser.getLibraryID(), "1234"));
        assertEquals(false, testLibrary.checkCredentials(testUser.getLibraryID(), "1235"));
    }

}
