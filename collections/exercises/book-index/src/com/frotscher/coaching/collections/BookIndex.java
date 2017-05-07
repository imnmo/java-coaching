package com.frotscher.coaching.collections;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class BookIndex {
    
    private Map<String,Integer> bookIndex = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    
    public void addReference(String term, int pageNum) {
        bookIndex.put(term, pageNum);
    }
    
    public void printIndex() {
        bookIndex.forEach((k,v)->{
            System.out.println("term:"+ k + "," + "pageNum" + v);
        });
    }
    
    public int getSize() {
       return  bookIndex.size();
    }
    
    public Map<String,Integer> getBookIndexMap(){
        return bookIndex;
    }

    @Override
    public String toString() {
        return Arrays.toString(bookIndex.entrySet().toArray()) ;
    }
}

