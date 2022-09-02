package ru.dkandakov.enumerated;

import ru.dkandakov.comparator.TimeComparator;
import ru.dkandakov.model.PrintJob;
import ru.dkandakov.comparator.NameComparator;


import java.util.Comparator;

public enum PrintSort {

    BY_NAME("Sort by name", NameComparator.INSTANCE::compare),
    BY_TIMEPPRINT("Sort by status", TimeComparator.INSTANCE::compare),
   /// BY_CREATED("Sort by created", CreatedComparator.INSTANCE::compare),
    BY_DEFAULT("Without sort", null);


    private final String name;


    private final Comparator<PrintJob> comparator;


    public static PrintSort toSort( final String value) {
        if (value == null || value.isEmpty()) return BY_DEFAULT;
        for ( final PrintSort sort : values()) {
            if (sort.name().equals(value)) return sort;
        }
        return BY_DEFAULT;
    }

    PrintSort( final String displayName,  final Comparator<PrintJob> comparator) {
        this.name = displayName;
        this.comparator = comparator;
    }

    public  String getName() {
        return this.name;
    }

    public  Comparator<PrintJob> getComparator() {
        return this.comparator;
    }
}