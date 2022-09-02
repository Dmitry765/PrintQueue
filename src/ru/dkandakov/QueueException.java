package ru.dkandakov;


public class QueueException extends Exception {

    public QueueException(String overflow) {
        super(overflow);
    }
}
