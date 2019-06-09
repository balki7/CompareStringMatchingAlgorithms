package com.balki;

/**
 * @author Balkı
 * @since 11/05/2019
 */
public class Report {
    private long preprocessDuration;
    private long duration;
    private long numberOfComparisons;

    public Report(long preprocessDuration, long duration, long numberOfComparisons) {
        this.preprocessDuration = preprocessDuration;
        this.duration = duration;
        this.numberOfComparisons = numberOfComparisons;
    }

    public long getPreprocessDuration() {
        return preprocessDuration;
    }

    public void setPreprocessDuration(long preprocessDuration) {
        this.preprocessDuration = preprocessDuration;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getNumberOfComparisons() {
        return numberOfComparisons;
    }

    public void setNumberOfComparisons(long numberOfComparisons) {
        this.numberOfComparisons = numberOfComparisons;
    }
}
