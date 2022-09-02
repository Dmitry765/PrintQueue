package ru.dkandakov.comparator;


import java.util.Comparator;

public enum TimeComparator implements Comparator<IHasTime> {

    INSTANCE;

    @Override
    public int compare(final IHasTime o1, final IHasTime o2) {
        if (o1 == null || o2 == null) return 0;
        if (o1.getTime() == null || o1.getTime() == null) return 0;
        return o1.getTime().compareTo(o2.getTime());
    }

}