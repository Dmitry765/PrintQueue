package ru.dkandakov;

public class PrinterApp {

    public static void main(String[] args) throws  InterruptedException {
        Queue queue = new CircularQueue();
        Printer printer = new Printer("Printer", queue);
        Producer producer = new Producer("Producer", queue);
        Thread threadProducer = new Thread(producer);
        Thread threadPrinter = new Thread(printer);
        threadPrinter.start();
        threadProducer.start();
        threadProducer.join();
        printer.halt();
    }

}
