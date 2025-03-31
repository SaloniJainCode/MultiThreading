package org.example;

class GenerateNumber{
    int max;
    int count = 1;

    public GenerateNumber(int max) {
        this.max = max;
    }

    public synchronized void generateEven() throws InterruptedException {
        while (count < max){
            while(count % 2 != 0){
                wait();
            }
            System.out.println("Even: "+count);
            count++;
            notify();
        }
    }

    public synchronized void generateOdd() throws InterruptedException {
        while (count < max){
            while(count % 2 == 0){
                wait();
            }
            System.out.println("Odd: "+count);
            count++;
            notify();
        }
    }
}

class EvenNumber implements Runnable{
    GenerateNumber generateNumber;
    public EvenNumber(GenerateNumber generateNumber) {
        this.generateNumber = generateNumber;
    }
    @Override
    public void run() {
        try {
            generateNumber.generateEven();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class OddNumber implements Runnable{
    GenerateNumber generateNumber;
    public OddNumber(GenerateNumber generateNumber) {
        this.generateNumber = generateNumber;
    }
    @Override
    public void run() {
        try {
            generateNumber.generateOdd();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class EvenOddNumber{
    public static void main(String[] args) {
        GenerateNumber number = new GenerateNumber(10);

        Thread evenThread = new Thread(new EvenNumber(number));
        Thread oddThread = new Thread(new OddNumber(number));

        evenThread.start();
        oddThread.start();
    }
}
