package com.balki.compare;

import com.balki.Report;
import com.balki.data.FileType;
import com.balki.search.Search;
import com.balki.util.FileOperations;
import com.balki.util.TimeOperations;
import com.balki.util.Util;
import com.sun.deploy.util.ArrayUtil;

import java.util.*;

/**
 * @author Balkı
 * @since 23/03/2019
 */
public class CompareSearchs {
    public void compare(Search... searchAlgos) throws Exception {
        String inFilePatternContent = FileOperations.readFile("./patterns/in_file.txt");
        String[] inFilePatterns = inFilePatternContent.split("\n");

        String notInFilePatternContent = FileOperations.readFile("./patterns/not_in_file.txt");
        String[] notInFilePatterns = notInFilePatternContent.split("\n");

        Map<FileType, Map<String, Map<Boolean, Map<Integer, List>>>> fileTypeMap = new HashMap<>();

        for(FileType type : FileType.values()) {
            fileTypeMap.put(type, new HashMap());

            String content = FileOperations.readFile(FileOperations.getFileName(type, 1));
            for(Search search : searchAlgos) {
                fileTypeMap.get(type).put(search.getClass().getSimpleName(), new HashMap());
                fileTypeMap.get(type).get(search.getClass().getSimpleName()).put(false, new HashMap());
                fileTypeMap.get(type).get(search.getClass().getSimpleName()).put(true, new HashMap());

                for (int j = 0; j < inFilePatterns.length; j++) {
                    fileTypeMap.get(type).get(search.getClass().getSimpleName()).get(true).put(inFilePatterns[j].length(), new ArrayList());
                }

                for (int j = 0; j < notInFilePatterns.length; j++) {
                    fileTypeMap.get(type).get(search.getClass().getSimpleName()).get(false).put(notInFilePatterns[j].length(), new ArrayList());
                }

                for (int j = 0; j < inFilePatterns.length; j++) {
                    String pattern = inFilePatterns[j];

                    for (int i = 0; i < Util.RETRY_COUNT; i++) {
                        search.reset();
                        TimeOperations.startTimer();
                        search.preprocess(pattern);
                        long preprocessDuration = TimeOperations.stopTimer();
                        TimeOperations.startTimer();
                        Set<Integer> indexList = search.search(pattern, content);
                        long duration = TimeOperations.stopTimer();
                        Util.report(search, type, pattern, indexList, preprocessDuration + "\t" +duration + "\t" + search.getNumberOfComparisons());
                        fileTypeMap.get(type).get(search.getClass().getSimpleName()).get(true).get(pattern.length()).add(new Report(preprocessDuration, duration, search.getNumberOfComparisons()));
                    }

                }

                for (int j = 0; j < notInFilePatterns.length; j++) {
                    String pattern = notInFilePatterns[j];

                    for (int i = 0; i < Util.RETRY_COUNT; i++) {
                        search.reset();
                        TimeOperations.startTimer();
                        search.preprocess(pattern);
                        long preprocessDuration = TimeOperations.stopTimer();
                        TimeOperations.startTimer();
                        Set<Integer> indexList = search.search(pattern, content);
                        long duration = TimeOperations.stopTimer();
                        Util.report(search, type, pattern, indexList, preprocessDuration + "\t" + duration + "\t" + search.getNumberOfComparisons());
                        fileTypeMap.get(type).get(search.getClass().getSimpleName()).get(false).get(pattern.length()).add(new Report(preprocessDuration, duration, search.getNumberOfComparisons()));
                    }
                }
            }
        }

        StringBuffer reportContent = new StringBuffer();
        for(FileType fileType : fileTypeMap.keySet()){
            for(String algo : fileTypeMap.get(fileType).keySet()){
                for(Boolean foundStatus : fileTypeMap.get(fileType).get(algo).keySet()) {
                    for(Integer pattern : fileTypeMap.get(fileType).get(algo).get(foundStatus).keySet()) {
                        long totalPreprocessDuration = 0;
                        long totalDuration = 0;
                        long totalNumComparisons = 0;
                        List<Report> reports = fileTypeMap.get(fileType).get(algo).get(foundStatus).get(pattern);
                        for(Report report : reports){
                            totalPreprocessDuration += report.getPreprocessDuration();
                            totalDuration += report.getDuration();
                            totalNumComparisons += report.getNumberOfComparisons();
                        }
                        reportContent.append(fileType.getFolderName() + "\t" + foundStatus+ "\t" +
                                 pattern + "\t" + (totalPreprocessDuration/reports.size())+ "\t" +
                                (totalDuration/reports.size()) + "\t" + (totalNumComparisons/reports.size()) + "\t" + algo).append("\n");
                    }
                }
            }
        }
        FileOperations.writeFile("./report.txt", reportContent.toString());
    }
}

