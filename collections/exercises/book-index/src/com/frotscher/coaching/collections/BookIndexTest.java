package com.frotscher.coaching.collections;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class BookIndexTest {
    
    //create based on number of pages
    int[] arrayForIndexing = new int[22];
    int intItem;
    
    BookReader bookReader = new BookReader();
    BookIndex bookIndex = new BookIndex();

    @Test
    public void test() {

        bookReader.getPageCount();

       System.out.println(bookReader.getWordsOnPage(1));
       
       for (intItem= 0;intItem <= arrayForIndexing.length-1;intItem++ ){
           List<String> wordOnPage = bookReader.getWordsOnPage(intItem);
           wordOnPage.forEach(item -> bookIndex.addReference(item, intItem));
       }
       int size = bookIndex.getSize();
       System.out.println(size);
       System.out.println(bookIndex.toString());
   
//       TreeMap<Integer, String> sortedKeys =
//               new TreeMap<Integer, String>();

//       bookIndex.getBookIndexMap().entrySet()
//                                  .stream()
//                                  .forEach(item -> item.getKey().compareTo());

       System.out.println(bookIndex.toString());
    }
    
}
