package ru.dkandakov.model;


public class PrintJobPdf extends PrintJob{

    private String typeDocum="DOC";
    private int timePrint=2;
    private String nameDocum;
    private int countPage;
    private String sizePaper;

    public PrintJobPdf(String nameDocum, int countPage, String sizePaper ) {
        this.nameDocum = nameDocum;
        this.countPage = countPage;
        this.sizePaper = sizePaper;
    }

    public String getNameDocum() {
        return this.nameDocum;
    }

    public String getSizePaper() {
        return this.sizePaper;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public Integer getTimePrint() {
        return timePrint;
    }

    @Override
    public String getName() {
        return this.jobName;
    }

}



