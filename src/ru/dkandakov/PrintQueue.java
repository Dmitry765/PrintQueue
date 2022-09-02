package ru.dkandakov;

import ru.dkandakov.model.PrintJob;

import java.util.ArrayList;


public class PrintQueue implements Queue {

   public ArrayList<PrintJob> jobs = new ArrayList <>();

    public int index;

    public PrintQueue() {
        this.index = 0;
    }


    @Override
    public int getNumberOfJobs() {
        return index--;
    }

    @Override
    public void printStop() {
        int index = 1;
        for ( final PrintJob printjob : jobs) {
            if (printjob == null) continue;
            System.out.println(index + ". " + printjob.getNameDocum() + " : " + printjob.getCountPage());
            index++;
        }
    }

    @Override
    public void printRemove() {
        synchronized (this) {
            if (!jobs.isEmpty())
                jobs.remove(0);
            if (index > 0) {
                index--;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public void AddBck(PrintJob job)  {
        synchronized (this) {
            jobs.add(index,job);
            index++;
            notify();
        }
    }

    @Override
    public PrintJob removeFront() throws InterruptedException {
        synchronized (this) {
            if (isEmpty()) {
                wait();
            }
            PrintJob job = jobs.get(0);
            jobs.remove(0) ;
            if (index > 0) {
                index--;
            }
            notify();
            return job;
        }
    }

}

