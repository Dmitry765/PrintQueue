package ru.dkandakov;

import ru.dkandakov.model.PrintJob;

public class Printer implements Runnable {
    public String name;
    public Queue queue;
    private boolean halt = false;
    public final long MILLIS_PER_PAGE = 50;
    QueueFinish queuefinish = QueueFinish.getInstance();

    public Printer(String name, Queue queue) {
        this.name = name;
        this.queue = queue;
    }

    public void halt() {
        this.halt = true;
    }

    public String getPrinterName() {
        return this.name;
    }

    @Override
    public void run() {
        System.out.println(String.format("[%s]: Turn on...", getPrinterName()));
        System.out.println(String.format("[%s]: Waiting for print job", getPrinterName()));
        try {
            PrintJob job;
            while (!halt && (job = queue.removeFront()) != null) {
                System.out.println(String.format("[%s]: Printing '%s'...", getPrinterName(), job.getJobName()));
                long sleepTime = job.getTimePrint() * MILLIS_PER_PAGE*job.getCountPage();
                job.setTimeAll(sleepTime);
                System.out.println(job.getTimePrint() + "timePrint");
                System.out.println(String.format("[%s]: Waiting for print job", getPrinterName()));
                Thread.sleep(sleepTime);
                System.out.println(String.format("[%s]: '%s' ok.", getPrinterName(), job.getJobName()));
                queuefinish.add(job);
            }
            System.out.println(String.format("[%s]: Turn off", getPrinterName()));
        } catch (InterruptedException ignored) {
        }
    }

}
