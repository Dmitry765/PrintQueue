package ru.dkandakov.model;


import ru.dkandakov.comparator.IWBS;

public abstract class PrintJob implements IWBS {

    public String jobName;
    public int NumberOfPages;
    public int TimePrint;
    private long timeAll;

    public String getJobName() {
        return this.jobName;

    }

    public Integer getNumberOfPages() {
        return NumberOfPages;
    }

    public Integer getTimePrint() {
        return TimePrint;
    }


    public Integer getCountPage() {
        return null;
    }

    public String getSizePaper() {
        return null;
    }

     public abstract String getNameDocum();

    public long getTimeAll() {
        return timeAll;
    }

    public String getTime() {
        return String.valueOf(timeAll);
    }

    public  void setTimeAll(long timeAll) {
        this.timeAll=timeAll;
    }
}


