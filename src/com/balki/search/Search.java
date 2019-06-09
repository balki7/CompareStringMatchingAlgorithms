package com.balki.search;

import java.util.Set;

/**
 * @author Balkı
 * @since 23/03/2019
 */
public interface Search {
    void preprocess(String pattern);

    Set<Integer> search(String pattern, String text);

    long getNumberOfComparisons();

    void reset();
}
