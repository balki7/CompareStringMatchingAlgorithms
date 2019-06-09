package com.balki;

import com.balki.compare.CompareSearchs;
import com.balki.search.KnuthMorrisPrattSearch;
import com.balki.search.RabinKarpSearch;

/**
 * @author Balkı
 * @since 11/05/2019
 */
public class Compare {

    public static void main(String[] args) throws Exception {
        CompareSearchs compareSearchs = new CompareSearchs();
        compareSearchs.compare(new RabinKarpSearch(), new KnuthMorrisPrattSearch());
    }
}
