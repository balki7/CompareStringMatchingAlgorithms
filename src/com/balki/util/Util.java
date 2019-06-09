package com.balki.util;

import com.balki.data.FileType;
import com.balki.search.Search;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author Balkı
 * @since 23/03/2019
 */
public class Util {
    public final static int RETRY_COUNT = 6;

    public static void report(List<Long> list){
        long min = Long.MAX_VALUE;
        long max = -1;
        long total = 0;
        for(Long duration : list){
            if(duration < min){
                min = duration;
            }

            if(duration > max){
                max = duration;
            }

            total += duration;
        }
        double average = total / list.size();
        System.out.println("Min : " + min);
        System.out.println("Max : " + max);
        System.out.println("Avg : " + average);
    }

    public static void report(Search a, FileType type, String pattern, Set<Integer> indexList, String content) throws IOException {
        String fileName = a.getClass().getSimpleName() + type.name() + ".txt";
        FileOperations.writeFile(fileName, pattern + "\t" + content + "\t" + indexList );
    }
}
