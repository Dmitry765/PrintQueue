package ru.dkandakov;
import ru.dkandakov.model.PrintJob;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueueFinish {

    private static QueueFinish INSTANCE;

    private QueueFinish() {
    }

    public static synchronized QueueFinish getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QueueFinish();
        }
        return INSTANCE;
    }

    private final List<PrintJob> jobs = new ArrayList<>();


    public void add(final PrintJob job) {
        jobs.add(job);
    }

    public List<PrintJob> findAll(final Comparator comparator) {
        jobs.sort(comparator);
        return jobs;
    }

    public List<PrintJob> findAll() {
        return jobs;
    }

}