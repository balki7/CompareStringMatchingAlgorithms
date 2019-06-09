package com.balki.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Balkı
 * @since 11/05/2019
 */
public class FileSplitter {
    // version 3.0
    private static final String suffix = ".splitPart";

    /**
     * Split a file into multiples files.
     *
     * @param fileName   Name of file to be split.
     * @param mBperSplit maximum number of MB per file.
     * @throws IOException
     */
    public static List<Path> splitFile(final String destinationFolder, final String fileName, final int mBperSplit, boolean storeRemain) throws IOException {
        if (mBperSplit <= 0) {
            throw new IllegalArgumentException("mBperSplit must be more than zero");
        }

        List<Path> partFiles = new ArrayList<>();
        final long sourceSize = Files.size(Paths.get(fileName));
        final long bytesPerSplit = 1024L * 1024L * mBperSplit;
        final long numSplits = sourceSize / bytesPerSplit;
        final long remainingBytes = sourceSize % bytesPerSplit;
        int position = 0;

        try (RandomAccessFile sourceFile = new RandomAccessFile(fileName, "r");
             FileChannel sourceChannel = sourceFile.getChannel()) {

            for (; position < numSplits; position++) {
                //write multipart files.
                writePartToFile(destinationFolder, (position + 1), bytesPerSplit, position * bytesPerSplit, sourceChannel, partFiles);
            }

            if (remainingBytes > 0 && storeRemain) {
                writePartToFile(destinationFolder, (position + 1), remainingBytes, position * bytesPerSplit, sourceChannel, partFiles);
            }
        }
        return partFiles;
    }

    private static void writePartToFile(String destinationFolder, int order, long byteSize, long position, FileChannel sourceChannel, List<Path> partFiles) throws IOException {
        Path fileName = Paths.get(destinationFolder + order + suffix);
        try (RandomAccessFile toFile = new RandomAccessFile(fileName.toFile(), "rw");
             FileChannel toChannel = toFile.getChannel()) {
            sourceChannel.position(position);
            toChannel.transferFrom(sourceChannel, 0, byteSize);
        }
        partFiles.add(fileName);
    }
}

