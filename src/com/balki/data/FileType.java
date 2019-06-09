package com.balki.data;

/**
 * @author Balkı
 * @since 24/03/2019
 */
public enum FileType {
    _1MB("1mb", 1),
    _3MB("3mb", 3),
    _5MB("5mb", 5);

    final String folderName;
    final int splitCount;

    private FileType(String folderName, int splitCount){
        this.folderName = folderName;
        this.splitCount = splitCount;
    }

    public String getFolderName(){
        return folderName;
    }

    public int getSplitCount(){
        return splitCount;
    }
}
