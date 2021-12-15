package com.oreshkin_aa.calc.tokens;

public class Priority {

    public boolean isLeftAssociative;
    public boolean isRightAssociative;
    public int priority;

    /**
     * Empty priority
     */
    public Priority() {
        isLeftAssociative = false;
        isRightAssociative = false;
        priority = -1;
    }

    public Priority(boolean isLeftAssociative, boolean isRightAssociative, int priority) {
        this.isLeftAssociative = isLeftAssociative;
        this.isRightAssociative = isRightAssociative;
        this.priority = priority;
    }

    /**
     * Can token with this priority be stacked over rhs token in station
     * @param rhs
     * @return
     */
    public boolean canStackOver(Priority rhs) {
        if(rhs.priority < 0 || priority < 0)
            return true;

        if(rhs.priority > priority)
            return false;

        if(rhs.priority < priority)
            return true;

        return isRightAssociative;
    }
}
