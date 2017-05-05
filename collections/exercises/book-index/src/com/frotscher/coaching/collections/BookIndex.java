package com.frotscher.coaching.collections;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookIndex {
    
    private Map<String,Integer> bookIndex = new LinkedHashMap<>();
    
    public void addReference(String term, int pageNum) {
        bookIndex.put(term, pageNum);
    }
    
    public void printIndex() {
        bookIndex.forEach((k,v)->{
            System.out.println("term:"+ k + "," + "pageNum" + v);
        });
    }

    @Override
    public String toString() {
        return Arrays.toString(bookIndex.entrySet().toArray()) ;
    }
}
