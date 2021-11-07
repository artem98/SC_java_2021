package com.oreshkin_aa;

public class Comparator<T> implements java.util.Comparator<T> {

    private int mComparesCount;

    public int getComparesCount() {
        return mComparesCount;
    }

    public void setComparesCount(int mComparesCount) {
        this.mComparesCount = mComparesCount;
    }

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }
}
