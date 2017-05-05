package com.frotscher.coaching.collections;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BookIndexTest {
    
    @Test
    public void test() {
        
        BookReader bookReader = new BookReader();
        BookIndex bookIndex = new BookIndex();
        bookReader.getPageCount();

       System.out.println(bookReader.getWordsOnPage(0));

       //create based on number of pages
//       int[] arrayForIndexing = new int[22];
//       for (int intItem= 0;intItem <= arrayForIndexing.length-1;intItem++ ){
//           List<String> wordOnPage = bookReader.getWordsOnPage(intItem);
//           String word = wordOnPage.get(intItem);
//           bookIndex.addReference(word, intItem);
//       }
       System.out.println(bookIndex.toString());

    }
    
}
