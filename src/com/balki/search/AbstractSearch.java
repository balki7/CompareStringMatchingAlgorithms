package com.balki.search;


/**
 * @author Balkı
 * @since 23/03/2019
 */
public abstract class AbstractSearch implements Search {
    private long numberOfComparisons;

    public AbstractSearch(){
        numberOfComparisons = 0;
    }

    @Override
    public long getNumberOfComparisons() {
        return this.numberOfComparisons;
    }

    @Override
    public void reset(){
        this.numberOfComparisons = 0;
    }

    protected void incComparisons(){
        this.numberOfComparisons++;
    }

}
