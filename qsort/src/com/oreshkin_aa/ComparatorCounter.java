package com.oreshkin_aa;

import java.util.Comparator;

public class ComparatorCounter<T extends Comparable<T>> implements java.util.Comparator<T> {

    private int mComparesCount;
    private boolean mIsDefaultCompare = true;
    private Comparator<T> mCustomComparator;

    public ComparatorCounter() {
        mComparesCount = 0;
    }

    public ComparatorCounter(Comparator<T> comparator) {
        mComparesCount = 0;
        mCustomComparator = comparator;
        mIsDefaultCompare = false;
    }

    public void clearCount() {
        mComparesCount = 0;
    }

    public int getComparesCount() {
        return mComparesCount;
    }

    @Deprecated
    public void setComparesCount(int mComparesCount) {
        this.mComparesCount = mComparesCount;
    }

    @Override
    public int compare(T o1, T o2) {
        mComparesCount++;

        if(mIsDefaultCompare)
            return o1.compareTo(o2);
        return mCustomComparator.compare(o1, o2);
    }
}
