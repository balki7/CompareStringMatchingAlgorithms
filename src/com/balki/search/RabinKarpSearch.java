package com.balki.search;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Balkı
 * @since 11/05/2019
 */
public class RabinKarpSearch extends AbstractSearch{
    private int h = 1;
    private final int d = 256;
    private final int q = 101;

    @Override
    public void preprocess(String pattern) {
        int M = pattern.length();
        int i;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;
    }

    @Override
    public Set<Integer> search(String pattern, String text) {
        Set<Integer> list = new HashSet<>();

        int M = pattern.length();
        int N = text.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt

        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++)
        {
            p = (d*p + pattern.charAt(i))%q;
            t = (d*t + text.charAt(i))%q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++)
        {

            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            incComparisons();
            if ( p == t )
            {
                /* Check for characters one by one */
                for (j = 0; j < M; j++)
                {
                    incComparisons();
                    if (text.charAt(i+j) != pattern.charAt(j))
                        break;
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M){
                    list.add(i);
                }
            }

            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if ( i < N-M )
            {
                t = (d*(t - text.charAt(i)*h) + text.charAt(i+M))%q;

                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                    t = (t + q);
            }
        }

        return list;
    }
}
