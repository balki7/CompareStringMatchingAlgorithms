package com.balki.util;

import com.balki.data.FileType;

import java.io.*;
import java.util.List;

/**
 * @author Balkı
 * @since 23/03/2019
 */
public class FileOperations {
    public static void writeFile(String file, List<Integer> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

        for (Integer number : list) {
            bw.write(String.valueOf(number.intValue()));
            bw.write("\n");
        }

        bw.close();
    }

    public static void writeFile(String file, String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(content);
        bw.write("\n");
        bw.close();
    }

    public static String readFile(String file) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sCurrentLine;
        while ((sCurrentLine = br.readLine()) != null) {
            sb.append(sCurrentLine).append("\n");
        }
        return sb.toString();
    }

    public static String getFileName(FileType type, long count) {
        return "./texts/" + type.getFolderName() + '/' + count + ".splitPart";
    }
}
