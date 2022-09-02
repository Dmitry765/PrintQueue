package ru.dkandakov;

import ru.dkandakov.model.PrintJob;

public interface Queue {

    public void AddBck(PrintJob job) ;

    public PrintJob removeFront() throws InterruptedException;

    public boolean isEmpty();

    public int getNumberOfJobs();

    public void printStop();

    public void  printRemove();

}
