package ru.dkandakov;

import ru.dkandakov.enumerated.PrintSort;
import ru.dkandakov.model.PrintJob;
import ru.dkandakov.model.PrintJobDoc;
import ru.dkandakov.model.PrintJobPdf;
import ru.dkandakov.model.PrintJobRtf;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Controller implements Runnable {
    public String name;
    public Queue queue;
    public Scanner scanner;
    PrintJob printJob;
    private boolean isCancel = false;

    public Controller(String name, Queue queue) {
        this.queue = queue;
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public String getProducerName() {
        return this.name;
    }


    private void processCommand(final String command)  {
        if (command == null || command.isEmpty()) return;
        switch (command) {
            case CommandConst.PRINT_REMOVE:
                this.cancel();
                queue.printRemove();
                break;
            case CommandConst.PRINT_STOP:
                this.cancel();
                queue.printStop();
                break;
            case CommandConst.PRINT_AVG:
                double sum = 0;
                QueueFinish queuefinish = QueueFinish.getInstance();
                List<PrintJob> printJobsAvg = queuefinish.findAll();
                int counters = 1;
                for ( final PrintJob printjob : printJobsAvg) {
                    if (printjob == null) continue;
                    sum += Double.valueOf( printjob.getTimeAll() );
                    counters++;
                }
                double average = sum/counters;
                System.out.println("PRINT AVG " + average);
                break;
            case CommandConst.PRINT_LIST:
                System.out.println("[PRINT LIST]");
                System.out.println("ENTER SORT");
                System.out.println(Arrays.toString(PrintSort.values()));
                final String sortType = TerminalUtil.nextLine();
                final PrintSort printSort = PrintSort.toSort(sortType);
                QueueFinish queefinish = QueueFinish.getInstance();
                List<PrintJob> printJobs;
                if (printSort.getComparator()==null){
                    printJobs = queefinish.findAll();
                }
                else{
                    printJobs = queefinish.findAll(printSort.getComparator());
                }
                int index = 1;
                for ( final PrintJob printjob : printJobs) {
                    if (printjob == null) continue;
                    System.out.println(index + ". " + printjob.getNameDocum() + ": " + printjob.getCountPage() + ": " + printjob.getTimeAll()+ ": " + printjob.getSizePaper());
                    index++;
                }
                break;
            case CommandConst.PRINT_CREATE:
                System.out.println("[PRINT CREATE]");
                System.out.println("TYPE DOCUM [DOC, RTF, PDF]");
                final String type = TerminalUtil.nextLine();
                System.out.println("ENTER COUNT LIST");
                final String countlist = TerminalUtil.nextLine();
                System.out.println("ENTER NAME DOCUM");
                final String namedocum = TerminalUtil.nextLine();
                if ("".equals(countlist)||(!"DOC".equals(type)&&!"RTF".equals(type)&&!"PDF".equals(type))) {
                    System.out.println("[ERROR] NOT CORRECT TYPE");
                    break;
                }
                else System.out.println("[OK]");
                printJob = createPrintJob(type, countlist, namedocum);
                queue.AddBck(printJob);
                break;
        }
    }

    public PrintJob createPrintJob (String type, String countlist, String namedocum) {

        PrintJob printJob = null;
        switch (type) {
            case "DOC":
                printJob =  new PrintJobDoc(namedocum, new Integer(countlist), "A4");
                break;
            case "RTF":
                printJob =  new PrintJobRtf(namedocum, new Integer(countlist), "A2");
                break;
            case "PDF":
                printJob =  new PrintJobPdf(namedocum, new Integer(countlist), "A1");
                break;
        }
        return printJob;
    }

    public void cancel()
    {
        this.isCancel = true;
    }

    @Override
    public void run() {
        try {
            while (!isCancel&&!Thread.currentThread().isInterrupted()) {
               Thread.sleep(1);
                System.out.println("ENTER COMMAND:");
                String valstr= scanner.nextLine();
                System.out.println("yoe type " + valstr);
                processCommand(valstr);
           }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
