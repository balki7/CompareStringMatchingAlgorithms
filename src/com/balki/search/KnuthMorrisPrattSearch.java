package com.balki.search;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Balkı
 * @since 11/05/2019
 */
public class KnuthMorrisPrattSearch extends AbstractSearch {
    private int lps[];

    @Override
    public void preprocess(String pattern) {
        int M = pattern.length();

        lps = new int[M];

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pattern, M, lps);
    }

    @Override
    public Set<Integer> search(String pattern, String text) {
        Set<Integer> list = new HashSet<>();

        int M = pattern.length();
        int N = text.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern

        int j = 0; // index for pat[]

        int i = 0; // index for txt[]
        while (i < N) {
            incComparisons();
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }

            if (j == M) {
                list.add(i-j);
                j = lps[j - 1];
            }
            // mismatch after j matches
            else {
                if (i < N && pattern.charAt(j) != text.charAt(i)) {
                    // Do not match lps[0..lps[j-1]] characters,
                    // they will match anyway
                    if (j != 0) {
                        j = lps[j - 1];
                    } else {
                        i = i + 1;
                    }
                }
            }
        }
        return list;
    }

    private void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}
